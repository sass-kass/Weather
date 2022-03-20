package com.example.weatherapp.service.dataclasses.fivedayweather

data class FiveDayMain(
    val temp: Double,
    val feelsLike: Double,
    val tempMax: Double,
    val tempMin: Double,
    val pressure: Double,
    val seaLevel: Double,
    val grndLevel: Double,
    val humidity: Int,
    val tempKf: Double
)
