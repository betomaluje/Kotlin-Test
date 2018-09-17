package com.betomaluje.android.weathertest.presenter

import com.betomaluje.android.weathertest.classes.Weather
import com.betomaluje.android.weathertest.model.WeatherModel
import java.util.*


class WeatherPresenter(iWeatherPresenter: IWeatherPresenter, woeid: String) : WeatherModel.IWeather {

    interface IWeatherPresenter {
        fun onDataStart()
        fun onDataSuccess(weatherData: List<Weather>)
        fun onDataError()
    }

    val tomorrow: Date
    val calendar = Calendar.getInstance()

    val woeid = woeid

    val iWeatherPresenter = iWeatherPresenter

    init {
        calendar.add(Calendar.DAY_OF_YEAR, 1)

        tomorrow = calendar.time
        calendar.time = tomorrow
    }

    fun getYear() = calendar.get(Calendar.YEAR)

    fun getMonth() = calendar.get(Calendar.MONTH) + 1 //0-based index

    fun getDay() = calendar.get(Calendar.DAY_OF_MONTH)

    fun getWeather() {
        iWeatherPresenter.onDataStart()
        val weatherModel = WeatherModel(this)
        weatherModel.getWeather(woeid, getYear(), getMonth(), getDay())
    }

    override fun onWeather(weather: List<Weather>) {
        iWeatherPresenter.onDataSuccess(weather)
    }

    override fun onError(error: String) {
        iWeatherPresenter.onDataError()
    }
}