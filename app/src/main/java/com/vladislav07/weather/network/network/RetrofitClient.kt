package com.vladislav07.weather.network.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    private const val CONNECT_TIMEOUT: Long = 60 // 60 seconds
    private const val READ_WRITE_TIMEOUT: Long = 120 // 120 seconds

    private fun getClient() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(
            OkHttpClient().newBuilder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_WRITE_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(READ_WRITE_TIMEOUT, TimeUnit.SECONDS)
                .build()

        )
        .build()

    fun getWeatherApi(): WeatherApi = getClient().create(WeatherApi::class.java)

}