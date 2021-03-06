package com.betomaluje.android.weathertest.model

import com.betomaluje.android.weathertest.WeatherContract
import com.betomaluje.android.weathertest.classes.Weather
import com.betomaluje.android.weathertest.network.ApiInterface
import retrofit2.Call
import retrofit2.Callback

class WeatherModel(iWeatherPresenter: WeatherContract.IWeatherPresenter) {

    val iWeatherPresenter = iWeatherPresenter

    fun getWeather(woeid: String, year: Int, month: Int, day: Int) {
        val apiService = ApiInterface.create()

        val call = apiService.getCityDetails(woeid, year, month, day)

        call.enqueue(object : Callback<List<Weather>> {
            override fun onResponse(call: Call<List<Weather>>, response: retrofit2.Response<List<Weather>>?) {
                if (response != null) {
                    val weather: List<Weather>? = response.body()
                    weather?.let { iWeatherPresenter.onDataSuccess(weather) }
                } else {
                    iWeatherPresenter.onDataError("Empty response")
                }
            }

            override fun onFailure(call: Call<List<Weather>>, t: Throwable) {
                iWeatherPresenter.onDataError(t.toString())
            }
        })
    }
}