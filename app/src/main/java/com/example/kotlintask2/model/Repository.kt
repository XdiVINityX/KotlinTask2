package com.example.kotlintask2.model

interface Repository {
        fun getWeatherFromServer(): Weather
        fun getWeatherFromLocalStorageRussian(): List<Weather>
        fun getWeatherFromLocalStorageWorld(): List<Weather>
}