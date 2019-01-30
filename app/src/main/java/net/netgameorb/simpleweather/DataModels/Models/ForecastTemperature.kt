package net.netgameorb.simpleweather.DataModels.Models

import com.google.gson.annotations.SerializedName


data class ForecastTemperature(
    @SerializedName("Minimum") val minimumTemperature: Temperature? = null,
    @SerializedName("Maximum") val maximumTemperature: Temperature? = null
)