package net.netgameorb.simpleweather.DataModels.Api

import net.netgameorb.simpleweather.DataModels.Models.CityInfo
import net.netgameorb.simpleweather.DataModels.Models.ForecastResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherService
{
    @GET("/locations/v1/topcities/{amount}")
    fun getTopCities(@Path("amount") amount: Int, @Query("apikey") apiKey: String): Call<List<CityInfo>>

    @GET("/forecasts/v1/daily/1day/{cityId}")
    fun getTodayCityForecast(@Path("cityId") cityId: String, @Query("apikey") apiKey: String): Call<ForecastResponse>
}
