package com.example.kotlintask2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.kotlintask2.model.Repository
import com.example.kotlintask2.model.RepositoryImpl
import java.lang.Thread.sleep

class MainViewModel(
    private val liveDataToObserve : MutableLiveData<AppState> = MutableLiveData(),
    private val repositoryImpl : Repository = RepositoryImpl()
) : ViewModel() {

    fun getLiveData() = liveDataToObserve
    fun getWeather() = getDataFromLocalSource(true)
    fun getWeatherFromLocalStorageWorld() = getDataFromLocalSource(false)
    fun getWeatherFromLocalStorageRussian() = getDataFromLocalSource(true)
    private fun getDataFromLocalSource(isRuss : Boolean) {
        liveDataToObserve.value = AppState.Loading

        Thread {
            sleep(2000)

            liveDataToObserve.postValue(AppState.Success(
                    if (isRuss){
                        repositoryImpl.getWeatherFromLocalStorageRussian()
                    } else {
                        repositoryImpl.getWeatherFromLocalStorageWorld()
                    }
                )
            )

        }.start()
    }
}