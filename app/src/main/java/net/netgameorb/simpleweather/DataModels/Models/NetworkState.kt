package net.netgameorb.simpleweather.DataModels.Models

enum class NetworkState
{
    LOADING, // We loading requesting the data from the server
    SUCCESS, // We got a successful response
    ERROR // We got an error from the server
}