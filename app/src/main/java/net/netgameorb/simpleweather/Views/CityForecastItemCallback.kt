package net.netgameorb.simpleweather.Views

import androidx.recyclerview.widget.DiffUtil
import net.netgameorb.simpleweather.DataModels.Models.DisplayCityForecast

class CityForecastItemCallback: DiffUtil.ItemCallback<DisplayCityForecast>() {

    override fun areItemsTheSame(oldItem: DisplayCityForecast, newItem: DisplayCityForecast): Boolean {

        // We only need to check if the city id matches
        return oldItem.cityInfo.id == newItem.cityInfo.id
    }

    override fun areContentsTheSame(oldItem: DisplayCityForecast, newItem: DisplayCityForecast): Boolean {

        // We only need to check if the network state or the forecast data changed
        return oldItem.networkState == newItem.networkState &&
                oldItem.forecast?.dateTimeStamp == newItem.forecast?.dateTimeStamp &&
                oldItem.forecast?.forecastTemperature?.minimumTemperature?.value == newItem.forecast?.forecastTemperature?.minimumTemperature?.value &&
                oldItem.forecast?.forecastTemperature?.maximumTemperature?.value == newItem.forecast?.forecastTemperature?.maximumTemperature?.value &&
                oldItem.forecast?.dayIcon?.id == newItem.forecast?.dayIcon?.id &&
                oldItem.forecast?.nightIcon?.id == newItem.forecast?.dayIcon?.id
    }
}