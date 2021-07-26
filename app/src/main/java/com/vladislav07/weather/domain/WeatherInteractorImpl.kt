package com.vladislav07.weather.domain

import android.util.Log
import com.vladislav07.weather.domain.model.WeatherDomain
import io.reactivex.Single

class WeatherInteractorImpl(private val networkRepository: NetworkRepository) : WeatherInteractor {
    override fun getWeather(cityName: String) {
        networkRepository.getWeather(cityName)
    }

    override fun getShortWeatherForDay(dayNumber: Int, cityName: String) {
        TODO("Not yet implemented")
    }

    override fun getAllWeatherForFiveDays(cityName: String): Single<Map<Int,List<WeatherDomain>>> {
        return networkRepository.getWeatherForFiveDays(cityName).map {
            getListWeatherForFiveDays(it)
        }
    }
}

private fun getListWeatherForFiveDays(
    allDaysWeather: List<WeatherDomain>
): Map<Int,List<WeatherDomain>> {
    var dayCounter = 0
    val resultMap = mutableMapOf<Int,List<WeatherDomain>>()
    val dayList = mutableListOf<WeatherDomain>()

    for (index in allDaysWeather.indices) {
        allDaysWeather[index].dayNumber = dayCounter

        if (allDaysWeather[index] != allDaysWeather.last() && allDaysWeather[index].date != allDaysWeather[index + 1].date) {
            dayList.clear()
            dayCounter++
        }
    }

    for (i in 0..4){
        resultMap[i] = allDaysWeather.filter { it.dayNumber == i }
    }

    return resultMap
}



