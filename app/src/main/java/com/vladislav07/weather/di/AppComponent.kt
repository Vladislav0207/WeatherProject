package com.vladislav07.weather.di

import android.app.Application
import androidx.fragment.app.FragmentActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Scope

@Component(modules = [AppModule::class])
@AppScope
interface AppComponent {
    fun inject (activity : FragmentActivity)
    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: Application) : Builder

        fun build() : AppComponent
    }
}

@Scope
annotation class AppScope
