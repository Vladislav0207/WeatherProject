package com.vladislav07.weather.domain

import com.vladislav07.weather.domain.model.WeatherDomain
import io.reactivex.Single

interface WeatherInteractor {
    fun getWeather(cityName: String)
    fun getShortWeatherForDay(dayNumber: Int, cityName: String)
    fun getAllWeatherForDay(dayNumber: Int, cityName: String): Single<List<WeatherDomain>>
}