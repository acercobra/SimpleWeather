package net.netgameorb.simpleweather.DataModels.Repository

import net.netgameorb.simpleweather.DataModels.Api.WeatherService
import net.netgameorb.simpleweather.DataModels.Models.CityInfo
import net.netgameorb.simpleweather.DataModels.Models.ForecastResponse
import net.netgameorb.simpleweather.DataModels.NetworkLiveData
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherRepository
{
    private val service: WeatherService
    private val apiKey: String = "API_KEY_FOR_ACCU_WEATHER"

    init
    {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dataservice.accuweather.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(WeatherService::class.java)
    }

    fun getTopFiftyCities(): NetworkLiveData<List<CityInfo>>
    {
        val call = service.getTopCities(50, apiKey)
        return NetworkLiveData(call)
    }

    fun getTodayForecast(cityId: String): NetworkLiveData<ForecastResponse>
    {
        val call = service.getTodayCityForecast(cityId, apiKey)
        return NetworkLiveData(call)
    }
}