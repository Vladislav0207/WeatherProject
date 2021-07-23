package com.vladislav07.weather.network.repository

import android.util.Log
import com.vladislav07.weather.domain.NetworkRepository
import com.vladislav07.weather.domain.model.WeatherDomain
import com.vladislav07.weather.network.network.RetrofitClient
import io.reactivex.Single

class NetworkRepositoryImpl() : NetworkRepository {
    companion object {
        private const val API_KEY = "029a1117e7c730636aaad7fa7b3528e7"
    }

    override fun getWeather(cityName: String): Single<WeatherDomain> {
        return RetrofitClient.getWeatherApi()
            .getWeather(cityName, API_KEY, "metric", "ru")
            .map {
                WeatherDomain(
                    it.main.temp.toInt().toString(),
                    it.main.feels_like.toInt().toString(),
                    it.weather[0].description,
                    "https://openweathermap.org/img/w/${it.weather[0].icon}.png",
                    it.wind.speed.toString(),
                    it.main.humidity,
                    it.main.pressure,
                    it.dt_txt.split(" ")[0],
                    it.dt_txt.split(" ")[1]
                )
            }
            .doOnError {
                Log.e("internet error: ", it.message.toString())
                it.printStackTrace()
            }
    }

    override fun getWeatherForFiveDays(cityName: String): Single<List<WeatherDomain>> {
        return RetrofitClient.getWeatherApi()
            .getWeatherForFiveDays(cityName, API_KEY, "metric", "ru")
            .map { listWeatherResponse ->
                listWeatherResponse.list.map{
                    WeatherDomain(
                        it.main.temp.toInt().toString(),
                        it.main.feels_like.toInt().toString(),
                        it.weather[0].description,
                        "https://openweathermap.org/img/w/${it.weather[0].icon}.png",
                        it.wind.speed.toString(),
                        it.main.humidity,
                        it.main.pressure,
                        it.dt_txt.split(" ")[0],
                        it.dt_txt.split(" ")[1]
                    )
                }}
    }
}

