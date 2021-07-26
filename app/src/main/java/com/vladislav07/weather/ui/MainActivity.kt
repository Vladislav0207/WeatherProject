package com.vladislav07.weather.ui

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.vladislav07.weather.R
import com.vladislav07.weather.ui.fragments.FragmentsAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : FragmentActivity() {
    private lateinit var fragmentsAdapter: FragmentsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val cityList = mutableListOf<String>()
        cityList.add("Minsk")
        cityList.add("Brest")
        fragmentsAdapter = FragmentsAdapter(this,cityList)
        fragmentPager.adapter = fragmentsAdapter
        fragmentPager.isUserInputEnabled = false
        TabLayoutMediator(tabLayout,fragmentPager) {tab, position->
            tab.text = cityList[position]
        }.attach()
    }
}