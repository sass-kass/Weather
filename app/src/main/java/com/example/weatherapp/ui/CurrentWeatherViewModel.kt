package com.example.weatherapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.repository.WeatherRepository
import com.example.weatherapp.service.dataclasses.currentweather.WeatherResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentWeatherViewModel @Inject constructor(private val dataSource: WeatherRepository): ViewModel() {
    var currentWeather: MutableLiveData<WeatherResponse> = MutableLiveData<WeatherResponse>()

    fun getNewWeather(cityName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            currentWeather.postValue(dataSource.getCurrentWeather(cityName))
        }
    }
}