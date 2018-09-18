package com.betomaluje.android.weathertest

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.betomaluje.android.weathertest.adapters.WeatherRecyclerAdapter
import com.betomaluje.android.weathertest.classes.Weather
import com.betomaluje.android.weathertest.databinding.WeatherPageBinding
import com.betomaluje.android.weathertest.presenter.WeatherPresenter

class WeatherFragment : Fragment(), WeatherContract.IWeatherView {

    lateinit var binding: WeatherPageBinding
    lateinit var woeid: String

    companion object {
        fun newInstance(woeid: String): WeatherFragment {
            val fragment = WeatherFragment()
            val bundle = Bundle()
            bundle.putString("woeid", woeid)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        woeid = arguments?.get("woeid").toString()
    }

    private fun fetchWeather() {
        woeid.let { WeatherPresenter(this, woeid).getWeather() }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.weather_page, container, false)
        val myView: View = binding.root

        binding.buttonReload.setOnClickListener { fetchWeather() }

        return myView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchWeather()
    }

    override fun showLoading() {
        binding.let {
            binding.progressBar.visibility = View.VISIBLE
            binding.buttonReload.visibility = View.GONE
        }
    }

    override fun hideLoading() {
        binding.progressBar.visibility = View.GONE
        binding.buttonReload.visibility = View.VISIBLE
    }

    override fun onShowData(weatherData: List<Weather>) {
        binding.let {
            binding.recyclerView.layoutManager = LinearLayoutManager(activity)

            binding.recyclerView.adapter = WeatherRecyclerAdapter(weatherData, context)

            binding.progressBar.visibility = View.GONE
            binding.buttonReload.visibility = View.GONE
        }
    }

    override fun onDataError(error: String) {
        binding.let {
            binding.textViewError.text = error
        }
    }
}
