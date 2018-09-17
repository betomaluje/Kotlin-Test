package com.betomaluje.android.weathertest.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.betomaluje.android.weathertest.R
import com.betomaluje.android.weathertest.classes.Weather
import com.betomaluje.android.weathertest.utils.StringUtils
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.weather_row.view.*

class WeatherRecyclerAdapter(items: List<Weather>, context: Context?) : RecyclerView.Adapter<WeatherRecyclerAdapter.ViewHolder>() {

    val context = context
    val items = items

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.weather_row, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items.get(position))
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val view = view

        val imageViewWeather = view.imageView_weather
        val textViewWeatherName = view.textView_weatherName
        val textViewWeatherMin = view.textView_weatherMin
        val textViewWeatherMax = view.textView_weatherMax

        fun bind(weather: Weather) {
            weather?.let {
                Glide.with(view.context).load(weather.getImageSource()).into(imageViewWeather)
                textViewWeatherName.text = weather.weatherStateName
                textViewWeatherMin.text = StringUtils.formatTemperature(weather.minTemp)
                textViewWeatherMax.text = StringUtils.formatTemperature(weather.maxTemp)
            }
        }
    }
}