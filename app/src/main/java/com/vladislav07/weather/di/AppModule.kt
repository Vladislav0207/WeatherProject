package com.vladislav07.weather.di

import com.vladislav07.weather.network.network.RetrofitClient
import com.vladislav07.weather.network.network.WeatherApi
import dagger.Module
import dagger.Provides


@Module
class AppModule {
    @Provides
    @AppScope
    fun provideWeatherApi() : WeatherApi {
        return RetrofitClient.getWeatherApi()
    }
}