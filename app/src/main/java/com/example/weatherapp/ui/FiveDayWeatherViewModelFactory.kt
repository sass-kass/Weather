package com.example.weatherapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.repository.WeatherRepository

class FiveDayWeatherViewModelFactory(private val dataSource: WeatherRepository, private val cityName: String): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FiveDayWeatherViewModel::class.java))
            return FiveDayWeatherViewModel(dataSource, cityName) as T
        throw IllegalArgumentException("Unknown viewmodel class")
    }
}