package com.example.kotlintask2.model

interface Repository {
        fun getWeatherFromServer(): Weather
        fun getWeatherFromLocalStorage(): Weather
}