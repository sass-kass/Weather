package com.example.weatherapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.weatherapp.R
import com.example.weatherapp.service.dataclasses.fivedayweather.WeatherHourly

class WeatherAdapter : ListAdapter<WeatherHourly, WeatherAdapter.ViewHolder>(WeatherComparator()) {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val icon: ImageView = view.findViewById(R.id.item_icon)
        val temperature: TextView = view.findViewById(R.id.item_temperature)
        val description: TextView = view.findViewById(R.id.item_weather_description)
        val date: TextView = view.findViewById(R.id.item_date)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.weather_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = getItem(position)
        when(item.weather[0].icon) {
            "01d", "01n" -> viewHolder.icon.load(R.drawable.ic_clear_day)
            "02d", "02n" -> viewHolder.icon.load(R.drawable.ic_few_clouds)
            "03d", "03n" -> viewHolder.icon.load(R.drawable.ic_mostly_cloudy)
            "04d", "04n" -> viewHolder.icon.load(R.drawable.ic_broken_clouds)
            "09d", "09n" -> viewHolder.icon.load(R.drawable.ic_snow_weather)
            "10d", "10n" -> viewHolder.icon.load(R.drawable.ic_rainy_weather)
            "11d", "11n" -> viewHolder.icon.load(R.drawable.ic_storm_weather)
            "13d", "13n" -> viewHolder.icon.load(R.drawable.ic_snow_weather)
            "50d", "50n" -> viewHolder.icon.load(R.drawable.ic_cloudy_weather)
        }
        var string = item.main.temp.toString() + "°C"
        viewHolder.temperature.text = string
        string = item.weather[0].main + ": " + item.weather[0].description
        viewHolder.description.text = string
        string = item.dt_txt.map {
            if(it == ' ')
                '\n'
            else
                it
        }.joinToString("")
        viewHolder.date.text = string
    }

    class WeatherComparator: DiffUtil.ItemCallback<WeatherHourly>() {
        override fun areItemsTheSame(oldItem: WeatherHourly, newItem: WeatherHourly): Boolean {
            return oldItem.dt_txt == newItem.dt_txt
        }

        override fun areContentsTheSame(oldItem: WeatherHourly, newItem: WeatherHourly): Boolean {
            return oldItem == newItem
        }
    }

}