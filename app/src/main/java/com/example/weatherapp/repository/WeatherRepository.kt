package com.example.weatherapp.repository

import com.example.weatherapp.service.WebClient
import com.example.weatherapp.service.dataclasses.currentweather.WeatherResponse
import com.example.weatherapp.service.dataclasses.fivedayweather.FiveDayWeatherResponse
import javax.inject.Inject
import javax.inject.Singleton

private const val UNITS = "metric"
private const val LANGUAGE = "en"
private const val APPID = "107d5d66032d410e7af1df8271cc6098"
private const val TAG = "MyActivity"

@Singleton
class WeatherRepository @Inject constructor() {

    fun getCurrentWeather(cityName: String): WeatherResponse? {
        val currentWeather: WeatherResponse?

        val response = WebClient.getClient().getCurrentWeather(cityName, UNITS, LANGUAGE, APPID).execute()
        currentWeather = response.body()
        return currentWeather
    }

    fun getFiveDayWeather(cityName: String): FiveDayWeatherResponse? {
        val currentWeather: FiveDayWeatherResponse?

        val response = WebClient.getClient().getFiveDayWeather(cityName, UNITS, LANGUAGE, APPID).execute()
        currentWeather = response.body()
        return currentWeather
    }
}