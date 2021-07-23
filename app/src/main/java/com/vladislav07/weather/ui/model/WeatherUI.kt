package com.vladislav07.weather.ui.model

data class WeatherUI(
    val temp: String,
    val feelsLike: String,
    val description: String,
    val icon: String,
    val windSpeed: String,
    val humidity: String,
    val pressure: String,
    val date: String,
    val time: String
)
