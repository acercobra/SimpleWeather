package net.netgameorb.simpleweather.DataModels.Models

data class DisplayCityForecast(
    var cityInfo: CityInfo,
    var forecast: Forecast?,
    val networkState: NetworkState
)