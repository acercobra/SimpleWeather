package net.netgameorb.simpleweather.DataModels.Models

data class NetworkResponse<T>(
    val currentState: NetworkState,
    val response: T?,
    val errorCode: Long = 0,
    val errorMessage: String? = null
)