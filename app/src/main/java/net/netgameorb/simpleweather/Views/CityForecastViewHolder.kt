package net.netgameorb.simpleweather.Views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.widget.ContentLoadingProgressBar
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import net.netgameorb.simpleweather.DataModels.Models.DisplayCityForecast
import net.netgameorb.simpleweather.DataModels.Models.NetworkState
import net.netgameorb.simpleweather.DataModels.Models.WeatherIcon
import net.netgameorb.simpleweather.DataModels.Models.getUrl
import net.netgameorb.simpleweather.R

class CityForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val weatherIconImageView: ImageView = itemView.findViewById(R.id.weather_icon_imageview)
    private val cityNameTextView: TextView = itemView.findViewById(R.id.city_name_textview)
    private val lowTemperatureTextView: TextView = itemView.findViewById(R.id.low_temperature_textview)
    private val highTemperatureTextView: TextView = itemView.findViewById(R.id.high_temperature_textview)

    private var circularProgressDrawable: CircularProgressDrawable = CircularProgressDrawable(itemView.context)

    init {
        circularProgressDrawable.setColorSchemeColors(
            ContextCompat.getColor(itemView.context, R.color.colorPrimary),
            ContextCompat.getColor(itemView.context, R.color.colorAccent),
            ContextCompat.getColor(itemView.context, R.color.colorPrimaryDark)
        )
        circularProgressDrawable.strokeWidth = 10f
        circularProgressDrawable.centerRadius = 50f
        circularProgressDrawable.start()
    }


    fun bind(displayCityForecast: DisplayCityForecast) {
        cityNameTextView.text = displayCityForecast.cityInfo.name
        lowTemperatureTextView.text = displayCityForecast.forecast?.forecastTemperature?.minimumTemperature?.toString()
        highTemperatureTextView.text = displayCityForecast.forecast?.forecastTemperature?.maximumTemperature?.toString()

        if (displayCityForecast.networkState == NetworkState.LOADING) {
            showWeatherLoadingIcon()
        } else {
            showWeatherIcon(displayCityForecast.forecast?.dayIcon)
        }
    }

    private fun showWeatherLoadingIcon() {
        Glide.with(weatherIconImageView)
            .load(circularProgressDrawable)
            .into(weatherIconImageView)
    }

    private fun showWeatherIcon(icon: WeatherIcon?) {
        val requestOptions = RequestOptions()
            .placeholder(circularProgressDrawable)
            .error(R.drawable.ic_cloud_queue_black_24dp)

        Glide.with(weatherIconImageView)
            .applyDefaultRequestOptions(requestOptions)
            .load(icon?.getUrl())
            .into(weatherIconImageView)
    }


    companion object {

        fun newInstance(parent: ViewGroup): CityForecastViewHolder {
            val itemView =
                LayoutInflater.from(parent.context).inflate(R.layout.viewholder_city_forecast_row, parent, false)
            return CityForecastViewHolder(itemView)
        }
    }
}