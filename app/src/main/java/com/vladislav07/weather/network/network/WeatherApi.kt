package com.vladislav07.weather.network.network

import com.vladislav07.weather.network.model.ListWeatherResponse
import com.vladislav07.weather.network.model.WeatherResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("weather")
    fun getWeather(
        @Query("q") cityName: String,
        @Query("appid") key: String,
        @Query("units") units: String,
        @Query("lang") lang: String
    ): Single<WeatherResponse>

    @GET("forecast")
    fun getWeatherForFiveDays(
        @Query("q") cityName: String,
        @Query("appid") key: String,
        @Query("units") units: String,
        @Query("lang") lang: String
    ): Single<ListWeatherResponse>
}