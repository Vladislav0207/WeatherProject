package com.vladislav07.weather.ui.fragments.start

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vladislav07.weather.R
import com.vladislav07.weather.domain.WeatherInteractorImpl
import com.vladislav07.weather.network.repository.NetworkRepositoryImpl
import com.vladislav07.weather.ui.fragments.adapter.HorizontalWeatherAdapter
import com.vladislav07.weather.ui.fragments.adapter.VerticalWeatherAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_start.*

const val ARG_OBJECT = "city"

class StartFragment : Fragment() {
    val networkRep = NetworkRepositoryImpl()
    val domRep = WeatherInteractorImpl(networkRep)
    val viewModel = StartViewModel(domRep)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var cityName: String? = null
        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
            cityName = getString(ARG_OBJECT)
        }

        cityName?.let { city ->
            viewModel.getAllWeatherForFiveDays(city)
            val mapWeather = viewModel.mapWeatherFiveDayLiveData.value
            mapWeather?.let {
                val verticalAdapter = VerticalWeatherAdapter(it, context)
                verticalWeatherRecycler.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                verticalWeatherRecycler.adapter = verticalAdapter
            }
        }
    }
}