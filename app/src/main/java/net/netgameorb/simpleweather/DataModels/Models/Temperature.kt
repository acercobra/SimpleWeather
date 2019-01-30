package net.netgameorb.simpleweather.DataModels.Models

import com.google.gson.annotations.SerializedName

data class Temperature(
    @SerializedName("Value") val value: Int = 0,
    @SerializedName("Unit") val unit: String = "F",
    @SerializedName("UnitType") val unitType: Int = 18
)
{
    override fun toString(): String {
        return "$value$unit"
    }
}