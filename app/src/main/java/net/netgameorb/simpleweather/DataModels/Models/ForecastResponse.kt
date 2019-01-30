package net.netgameorb.simpleweather.DataModels.Models

import com.google.gson.annotations.SerializedName

data class ForecastResponse(
    @SerializedName("DailyForecasts") val forecasts: List<Forecast>? = null
)