package com.vladislav07.weather.domain.model


data class WeatherDomain(
    val temp: String,
    val feelsLike: String,
    val description: String,
    val icon: String,
    val windSpeed: String,
    val humidity: Int,
    val pressure: Int,
    val date: String,
    val time: String,
    var dayNumber: Int = 0
)
