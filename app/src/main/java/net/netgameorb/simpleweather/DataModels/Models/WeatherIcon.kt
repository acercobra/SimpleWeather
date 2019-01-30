package net.netgameorb.simpleweather.DataModels.Models

import com.google.gson.annotations.SerializedName
import java.text.DecimalFormat

fun WeatherIcon.getUrl(): String
{
    val urlId = DecimalFormat("00").format(id)
    return "https://vortex.accuweather.com/adc2010/m/images/icons/600x212/slate/$urlId.png"
}

data class WeatherIcon(
    @SerializedName("Icon") val id: Int = 0,
    @SerializedName("IconPhrase") val name: String = ""
)