package com.vladislav07.weather.domain

import com.vladislav07.weather.domain.model.WeatherDomain
import io.reactivex.Single

interface NetworkRepository {
    fun getWeather(cityName: String): Single<WeatherDomain>
    fun getWeatherForFiveDays(cityName: String): Single<List<WeatherDomain>>

}