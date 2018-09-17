package com.betomaluje.android.weathertest.network

import com.betomaluje.android.weathertest.classes.Weather
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

public interface ApiInterface {
    @GET("location/{woeid}/{year}/{month}/{day}")
    abstract fun getCityDetails(@Path("woeid") woeid: String,
                                @Path("year") year: Int,
                                @Path("month") month: Int,
                                @Path("day") day: Int): Call<List<Weather>>

    companion object Factory {
        val BASE_URL = "https://www.metaweather.com/api/"
        fun create(): ApiInterface {
            val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}