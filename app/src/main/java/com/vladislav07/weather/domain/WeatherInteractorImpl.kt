package com.vladislav07.weather.domain

import com.vladislav07.weather.domain.model.WeatherDomain
import io.reactivex.Single

class WeatherInteractorImpl(private val networkRepository: NetworkRepository) : WeatherInteractor {
    override fun getWeather(cityName: String) {
        networkRepository.getWeather(cityName)
    }

    override fun getShortWeatherForDay(dayNumber: Int, cityName: String) {
        TODO("Not yet implemented")
    }

    override fun getAllWeatherForDay(dayNumber: Int, cityName: String): Single<List<WeatherDomain>> {
        return networkRepository.getWeatherForFiveDays(cityName).map {
            getListWeatherForDay(it, dayNumber)
        }
    }
}

private fun getListWeatherForDay(
    allDaysWeather: List<WeatherDomain>,
    dayNumber: Int
): List<WeatherDomain> {
    var dayCounter = 1
    val result = mutableListOf<WeatherDomain>()
    for (index in allDaysWeather.indices) {

        if (dayCounter == dayNumber) {
            result.add(allDaysWeather[index])
        }

        if (allDaysWeather[index] != allDaysWeather.last() && allDaysWeather[index].date != allDaysWeather[index + 1].date) {
            dayCounter++
        }
    }
    return result
}
