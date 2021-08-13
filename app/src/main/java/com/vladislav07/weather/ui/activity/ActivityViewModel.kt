package com.vladislav07.weather.ui.activity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vladislav07.weather.domain.WeatherInteractorImpl
import com.vladislav07.weather.ui.model.WeatherUI
import com.vladislav07.weather.ui.toWeatherUI
import io.reactivex.Single

class ActivityViewModel(private val weatherInteractorImpl: WeatherInteractorImpl) : ViewModel() {


    val cityListLiveData = MutableLiveData<MutableList<String>>()

    fun getAllWeatherForFiveDays(cityName: String): Single<Map<Int, List<WeatherUI>>> {
        return weatherInteractorImpl.getAllWeatherForFiveDays(cityName)
            .map { map -> map.mapValues { entry -> entry.value.map { it.toWeatherUI() } } }
            .doOnError {
                Log.e("ViewModelStart",it.localizedMessage!!.toString())
            }
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