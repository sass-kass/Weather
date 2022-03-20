package com.example.weatherapp.service.dataclasses.fivedayweather

import com.example.weatherapp.service.dataclasses.Clouds
import com.example.weatherapp.service.dataclasses.Weather

data class WeatherHourly(
    val dt: Int,
    val main: FiveDayMain,
    val weather: List<Weather>,
    val clouds: Clouds,
    val wind: FiveDayWind,
    val visibility: Int,
    val pop: Double,
    val sys: FiveDaySys,
    val dt_txt: String
)
