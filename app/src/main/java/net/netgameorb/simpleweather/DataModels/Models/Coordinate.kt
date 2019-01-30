package net.netgameorb.simpleweather.DataModels.Models

import com.google.gson.annotations.SerializedName

data class Coordinate(
    @SerializedName("Latitude") val latitude: Double = 0.0,
    @SerializedName("Longitude") val longitude: Double = 0.0
)