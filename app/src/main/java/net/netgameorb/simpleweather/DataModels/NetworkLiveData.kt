package net.netgameorb.simpleweather.DataModels

import androidx.lifecycle.MutableLiveData
import net.netgameorb.simpleweather.DataModels.Models.NetworkResponse
import net.netgameorb.simpleweather.DataModels.Models.NetworkState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NetworkLiveData<T>(private val initialCall: Call<T>): MutableLiveData<NetworkResponse<T>>(), Callback<T>
{
    private var call: Call<T> = initialCall

    override fun onActive() {
        super.onActive()

        if (call.isExecuted || call.isCanceled)
        {
            return
        }

        call.enqueue(this)
    }

    private fun isRunning(): Boolean
    {
        return value?.currentState == NetworkState.LOADING
    }

    fun refresh()
    {
        if (isRunning())
        {
            // Avoid refreshing if we already requesting the data
            return
        }

        call = call.clone()
        startNetworkCall()
    }

    private fun startNetworkCall()
    {
        value = NetworkResponse(NetworkState.LOADING, null)
        call.enqueue(this)
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        // Post our error
        postValue(NetworkResponse(NetworkState.ERROR, null, -1, t.message))
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        // Post our success
        postValue(NetworkResponse(NetworkState.SUCCESS, response.body()))
    }
}