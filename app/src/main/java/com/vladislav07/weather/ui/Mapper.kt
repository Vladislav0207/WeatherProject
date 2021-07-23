package com.vladislav07.weather.ui

import com.vladislav07.weather.domain.model.WeatherDomain
import com.vladislav07.weather.ui.model.WeatherUI

fun WeatherDomain.toWeatherUI() =
    WeatherUI(
        "$temp°",
        "$feelsLike°",
        description,
        icon,
        "$windSpeed м/с",
        "$humidity%",
        "$pressure гПа",
        date,
        time
    )