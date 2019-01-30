package net.netgameorb.simpleweather.Views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.netgameorb.simpleweather.R
import net.netgameorb.simpleweather.ViewModels.WeatherViewModel

class CitiesForecastListFragment : Fragment() {

    private lateinit var weatherRecyclerView: RecyclerView
    private lateinit var cityForecastAdapter: CityForecastAdapter
    private lateinit var emptyScreenGroup: View
    private lateinit var loadingScreenGroup: View
    private lateinit var weatherViewModel: WeatherViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_cities_forecast_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        weatherRecyclerView = view.findViewById(R.id.weather_recyclerview)
        emptyScreenGroup = view.findViewById(R.id.empty_screen_group)
        loadingScreenGroup = view.findViewById(R.id.loading_screen_group)

        cityForecastAdapter = CityForecastAdapter()
        weatherRecyclerView.adapter = cityForecastAdapter
        weatherRecyclerView.layoutManager = GridLayoutManager(view.context, 3).apply {
            // We need to let the layoutManager know that each item only take up one cell in the grid
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return 1
                }
            }
        }

        // Show loading screen while we wait for the cities forecast
        showLoadingScreen()

        weatherViewModel = ViewModelProviders.of(activity!!).get(WeatherViewModel::class.java)
        weatherViewModel.getCitiesForecastList().observe(this, Observer {
            if (it.isEmpty())
            {
                // Show Empty Screen
                showEmptyScreen()
            }
            else
            {
                // Show the cities forecast
                showCitiesForecastList()
                cityForecastAdapter.submitList(it)
            }
        })
    }


    private fun showEmptyScreen() {
        weatherRecyclerView.visibility = View.GONE
        emptyScreenGroup.visibility = View.VISIBLE
        loadingScreenGroup.visibility = View.GONE
    }

    private fun showLoadingScreen() {
        weatherRecyclerView.visibility = View.GONE
        emptyScreenGroup.visibility = View.GONE
        loadingScreenGroup.visibility = View.VISIBLE
    }

    private fun showCitiesForecastList() {
        weatherRecyclerView.visibility = View.VISIBLE
        emptyScreenGroup.visibility = View.GONE
        loadingScreenGroup.visibility = View.GONE
    }
}