package com.betomaluje.android.weathertest.presenter

import com.betomaluje.android.weathertest.WeatherContract
import com.betomaluje.android.weathertest.classes.Weather
import com.betomaluje.android.weathertest.model.WeatherModel
import java.util.*


class WeatherPresenter(iWeatherView: WeatherContract.IWeatherView, woeid: String) : WeatherContract.IWeatherPresenter {

    val tomorrow: Date
    val calendar = Calendar.getInstance()

    val woeid = woeid

    val iWeatherView = iWeatherView

    init {
        calendar.add(Calendar.DAY_OF_YEAR, 1)

        tomorrow = calendar.time
        calendar.time = tomorrow
    }

    fun getYear() = calendar.get(Calendar.YEAR)

    fun getMonth() = calendar.get(Calendar.MONTH) + 1 //0-based index

    fun getDay() = calendar.get(Calendar.DAY_OF_MONTH)

    override fun onDataSuccess(weatherData: List<Weather>) {
        iWeatherView.hideLoading()
        iWeatherView.onShowData(weatherData)
    }

    override fun onDataError(error: String) {
        iWeatherView.hideLoading()
        iWeatherView.onDataError(error)
    }

    fun getWeather() {
        iWeatherView.showLoading()
        val weatherModel = WeatherModel(this)
        weatherModel.getWeather(woeid, getYear(), getMonth(), getDay())
    }
}