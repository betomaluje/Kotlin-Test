package com.betomaluje.android.weathertest.utils

import java.text.SimpleDateFormat
import java.util.*


class StringUtils {

    companion object {
        fun formatTemperature(temperature: Double?): String {
            return String.format("%.1f °C", temperature)
        }

        fun formatDate(rawDate: String?): String {
            val fmt = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val date = fmt.parse(rawDate)

            val fmtOut = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            return fmtOut.format(date)
        }
    }
}