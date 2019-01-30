package net.netgameorb.simpleweather.DataModels.Models

import com.google.gson.annotations.SerializedName

data class Forecast(
    @SerializedName("EpochDate") val dateTimeStamp: Long,
    @SerializedName("Temperature") val forecastTemperature: ForecastTemperature? = null,
    @SerializedName("Day") val dayIcon: WeatherIcon? = null,
    @SerializedName("Night") val nightIcon: WeatherIcon? = null
)