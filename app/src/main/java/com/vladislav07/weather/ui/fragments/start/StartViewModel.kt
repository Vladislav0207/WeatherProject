package com.vladislav07.weather.ui.fragments.start

import android.util.Log
import androidx.lifecycle.ViewModel
import com.vladislav07.weather.domain.WeatherInteractorImpl
import com.vladislav07.weather.ui.model.WeatherUI
import com.vladislav07.weather.ui.toWeatherUI
import io.reactivex.Single


class StartViewModel(private val weatherInteractorImpl: WeatherInteractorImpl) : ViewModel() {


    fun getAllWeatherForDay(numberDay: Int, cityName: String): Single<List<WeatherUI>> {
       return weatherInteractorImpl.getAllWeatherForDay(numberDay, cityName)
           .map { list -> list.map { it.toWeatherUI() } }
           .doOnError {
               Log.e("ViewModelStart",it.localizedMessage!!.toString())
           }
    }

}