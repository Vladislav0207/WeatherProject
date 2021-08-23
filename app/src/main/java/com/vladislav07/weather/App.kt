package com.vladislav07.weather

import android.app.Application
import com.vladislav07.weather.di.AppComponent
import com.vladislav07.weather.di.DaggerAppComponent

class App : Application() {
    val appComponent : AppComponent by lazy{
        DaggerAppComponent.builder()
            .application(this)
            .build()
    }
}