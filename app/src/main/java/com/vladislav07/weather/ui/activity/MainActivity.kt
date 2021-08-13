package com.vladislav07.weather.ui.activity

import android.app.AlertDialog
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.vladislav07.weather.R
import com.vladislav07.weather.domain.WeatherInteractorImpl
import com.vladislav07.weather.network.repository.NetworkRepositoryImpl
import com.vladislav07.weather.ui.fragments.FragmentsAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : FragmentActivity() {

    val networkRepository = NetworkRepositoryImpl()
    val compositeDisposable = CompositeDisposable()
    val weatherInteractorImpl = WeatherInteractorImpl(networkRepository)
    val viewModel = ActivityViewModel(weatherInteractorImpl)
    private lateinit var fragmentsAdapter: FragmentsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        val cityList = viewModel.getCityList()
        fragmentsAdapter = FragmentsAdapter(this, viewModel.getCityList())
        fragmentPager.adapter = fragmentsAdapter
        fragmentPager.isUserInputEnabled = false
        viewModel.cityListLiveData.observe(this)
        {
            fragmentPager.adapter?.notifyDataSetChanged()
        }

        TabLayoutMediator(tabLayout, fragmentPager) { tab, position ->
            tab.text = cityList[position]
        }.attach()

        createLongTouchTabs()

        citySearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                compositeDisposable.add(
                    viewModel.getAllWeatherForFiveDays(query!!)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            viewModel.addToCityList(query)
                            addTab(query)
                            citySearch.setQuery("", false)
                        }, {
                            Toast.makeText(
                                applicationContext,
                                "Город не найден",
                                Toast.LENGTH_SHORT
                            ).show()
                        })
                )
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

    private fun addTab(title: String) {
        tabLayout.addTab(tabLayout.newTab().setText(title))
        createLongTouchTabs()

    }

    private fun createLongTouchTabs() {
        val tabStrip = tabLayout.getChildAt(0) as LinearLayout
        for (index in 0 until tabStrip.childCount) {
            tabStrip.getChildAt(index).setOnLongClickListener {
                vibrate()
                setAlertDialogByTab(index)
                true
            }
        }
    }

    private fun vibrate() {
        val vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator
        vibrator.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE))
    }


    private fun setAlertDialogByTab(index : Int) {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setMessage(R.string.alertDialogMessage)
        alertDialog.setPositiveButton(R.string.yes) { _, _ ->
            tabLayout.getTabAt(index)?.let { it1 -> tabLayout.removeTab(it1) }
        }
        alertDialog.setNegativeButton(R.string.no,null)
        alertDialog.show()
        }

    }

