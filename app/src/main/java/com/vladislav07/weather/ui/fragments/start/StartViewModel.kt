package com.vladislav07.weather.ui.fragments.start

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


class StartViewModel(private val weatherInteractorImpl: WeatherInteractorImpl) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    val mapWeatherFiveDayLiveData = MutableLiveData<Map<Int,List<WeatherUI>>>()

    fun getAllWeatherForFiveDays(cityName: String) {
       compositeDisposable.add(weatherInteractorImpl.getAllWeatherForFiveDays(cityName)
           .subscribeOn(Schedulers.io())
           .observeOn(AndroidSchedulers.mainThread())
           .subscribe(
               { map ->
                   mapWeatherFiveDayLiveData.value =
                       map.mapValues { entry -> entry.value.map { it.toWeatherUI() }
                   }
               },{
                   it.printStackTrace()
               }
           )
//           .map { map -> map.mapValues { entry -> entry.value.map { it.toWeatherUI() } } }
//           .doOnError {
//               Log.e("ViewModelStart",it.localizedMessage!!.toString())
//           }
       )
    }

}