package com.example.weatherapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.repository.WeatherRepository
import com.example.weatherapp.service.dataclasses.fivedayweather.FiveDayWeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FiveDayWeatherViewModel(private val dataSource: WeatherRepository, private val cityName: String): ViewModel() {
    var currentWeather: MutableLiveData<FiveDayWeatherResponse> = MutableLiveData<FiveDayWeatherResponse>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            currentWeather.postValue(dataSource.getFiveDayWeather(cityName))
        }
    }
}