package com.example.kotlintask2.viewmodel

import com.example.kotlintask2.model.Weather

sealed class AppState{
    data class Success(val weatherData : Weather) : AppState()
    data class Error(val error : Throwable) : AppState()
    object Loading : AppState()

}
