package net.netgameorb.simpleweather.ViewModels

import androidx.lifecycle.*
import net.netgameorb.simpleweather.DataModels.Models.*
import net.netgameorb.simpleweather.DataModels.NetworkLiveData
import net.netgameorb.simpleweather.DataModels.Repository.WeatherRepository

class WeatherViewModel: ViewModel()
{
    private val weatherRepository = WeatherRepository()
    private lateinit var topFiftyCitiesLiveData: NetworkLiveData<List<CityInfo>>
    private lateinit var citiesForecastMapLiveData: MediatorLiveData<HashMap<String, DisplayCityForecast>>
    private lateinit var citiesForecastListLiveData: LiveData<List<DisplayCityForecast>>

    fun getCitiesForecastList(): LiveData<List<DisplayCityForecast>>
    {
        if (!::citiesForecastMapLiveData.isInitialized)
        {
            // Create a map of cities forecast by city id
            citiesForecastMapLiveData = MediatorLiveData()
            citiesForecastMapLiveData.value = HashMap()

            // Create a sorted list of the cities forecast
            citiesForecastListLiveData = Transformations.switchMap(citiesForecastMapLiveData) { citiesForecastMap ->
                return@switchMap MutableLiveData<List<DisplayCityForecast>>().apply {
                    // Push the sorted list of cities forecast to all observers
                    postValue(citiesForecastMap.values.toMutableList().sortedBy {
                        it.cityInfo.name
                    })
                }
            }
        }

        return citiesForecastListLiveData
    }

    fun getTopFiftyCities(): NetworkLiveData<List<CityInfo>>
    {
        if (!::topFiftyCitiesLiveData.isInitialized)
        {
            topFiftyCitiesLiveData = weatherRepository.getTopFiftyCities()
        }

        return topFiftyCitiesLiveData
    }


    fun addCityForecast(cityInfo: CityInfo)
    {
        if (!::citiesForecastMapLiveData.isInitialized)
        {
            // We can't do anything yet since citiesForecastMapLiveData is not ready
            return
        }

        citiesForecastMapLiveData.value?.let { citiesForecastMap ->
            if (!citiesForecastMap.containsKey(cityInfo.id))
            {
                // Add the city forecast to the map and start the network request
                citiesForecastMap[cityInfo.id] = DisplayCityForecast(cityInfo, null, NetworkState.LOADING)
                citiesForecastMapLiveData.postValue(citiesForecastMap)

                citiesForecastMapLiveData.addSource(weatherRepository.getTodayForecast(cityInfo.id)) { response ->
                    // Update the map value
                    citiesForecastMap[cityInfo.id] = DisplayCityForecast(cityInfo, response.response?.forecasts?.firstOrNull(), response.currentState)
                    // Push the update to all observers
                    citiesForecastMapLiveData.postValue(citiesForecastMap)
                }
            }
        }

    }
}