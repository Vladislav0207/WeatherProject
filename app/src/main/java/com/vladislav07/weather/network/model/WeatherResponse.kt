package com.vladislav07.weather.network.model

data class ListWeatherResponse(
    val list: List<WeatherResponse>
)

data class WeatherResponse(
    val weather: List<Weather>,
    val main: Main,
    val visibility: Int,
    val wind: Wind,
    val dt_txt: String,
)

data class Coordinates(
    val lon: Double,
    val lat: Double
)

data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

data class Main(
    val temp: Double,
    val feels_like: Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Int,
    val humidity: Int
)

data class Wind(
    val speed: Double,
    val deg: Int
)