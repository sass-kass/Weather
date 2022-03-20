package com.example.weatherapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.repository.WeatherRepository

class CurrentWeatherViewModelFactory(private val dataSource: WeatherRepository): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CurrentWeatherViewModel::class.java))
            return CurrentWeatherViewModel(dataSource) as T
        throw IllegalArgumentException("Unknown viewmodel class")
    }
}
