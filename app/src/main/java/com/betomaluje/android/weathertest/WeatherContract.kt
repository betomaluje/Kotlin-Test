package com.betomaluje.android.weathertest

import com.betomaluje.android.weathertest.classes.Weather

interface WeatherContract {

    interface IWeatherPresenter {
        fun onDataSuccess(weatherData: List<Weather>)
        fun onDataError(error: String)
    }

    interface IWeatherView {
        fun showLoading()
        fun hideLoading()
        fun onShowData(weatherData: List<Weather>)
        fun onDataError(error: String)
    }

}