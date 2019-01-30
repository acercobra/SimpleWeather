package net.netgameorb.simpleweather.Views

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import net.netgameorb.simpleweather.DataModels.Models.DisplayCityForecast

class CityForecastAdapter : ListAdapter<DisplayCityForecast, CityForecastViewHolder>(CityForecastItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityForecastViewHolder {
        return CityForecastViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: CityForecastViewHolder, position: Int) {
        val displayCityForecast = getItem(position)
        holder.bind(displayCityForecast)
    }
}