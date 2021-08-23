package com.vladislav07.weather.ui.activity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vladislav07.weather.domain.WeatherInteractorImpl
import com.vladislav07.weather.ui.model.WeatherUI
import com.vladislav07.weather.ui.toWeatherUI
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ActivityViewModel(private val weatherInteractorImpl: WeatherInteractorImpl) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    val cityListLiveData = MutableLiveData<MutableList<String>>()

    fun getWeather(cityName: String) : Boolean {
        var result = false
        compositeDisposable.add(weatherInteractorImpl.getWeather(cityName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    result = true
                },{
                    result = false
                }
            )
        )
        return result
    }

    fun getCityList() : MutableList<String>{
        val cityList = mutableListOf<String>()
        cityList.add("Minsk")
        cityList.add("Brest")
        cityListLiveData.postValue(cityList)
        return cityList
    }

    fun addToCityList(cityName:String) {
        cityListLiveData.value?.add(cityName)
    }
}