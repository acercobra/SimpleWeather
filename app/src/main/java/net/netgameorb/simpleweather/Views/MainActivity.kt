package net.netgameorb.simpleweather.Views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import net.netgameorb.simpleweather.DataModels.Models.NetworkState
import net.netgameorb.simpleweather.R
import net.netgameorb.simpleweather.ViewModels.WeatherViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var weatherViewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        weatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel::class.java)

        showWeatherListFragment()

        fab.hide()

        weatherViewModel.getTopFiftyCities().observe(this, Observer {
            when (it.currentState) {
                NetworkState.LOADING -> {
                    fab.hide()
                }

                NetworkState.ERROR -> {
                    Snackbar.make(fab, R.string.could_not_get_top_cities, Snackbar.LENGTH_INDEFINITE)
                        .setAction(R.string.retry) {
                            weatherViewModel.getTopFiftyCities().refresh()
                        }.show()
                }

                NetworkState.SUCCESS -> {
                    fab.show()
                    fab.setOnClickListener { view ->
                        // we will pick a random city from the top 50 return
                        it.response?.random()?.let { cityInfo ->
                            weatherViewModel.addCityForecast(cityInfo)
                        }
                    }
                }
            }
        })
    }

    private fun showWeatherListFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_framelayout, CitiesForecastListFragment())
            .commit()
    }
}
