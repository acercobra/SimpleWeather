package net.netgameorb.simpleweather.DataModels.Models

import com.google.gson.annotations.SerializedName

data class CityInfo(
    @SerializedName("Key") val id: String = "",
    @SerializedName("EnglishName") val name: String = "",
    @SerializedName("GeoPosition") val coordinate: Coordinate? = null
)