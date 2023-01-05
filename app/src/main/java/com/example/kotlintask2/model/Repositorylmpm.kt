package com.example.kotlintask2.model

class RepositoryImpl : Repository {
    override fun getWeatherFromServer(): Weather {
        return Weather()
    }

    override fun getWeatherFromLocalStorageRussian(): List<Weather> {
        return getWorldCities()
    }

    override fun getWeatherFromLocalStorageWorld(): List<Weather> {
       return getWeatherFromLocalStorageWorld()
    }
}