package com.wvn.cwan.weatherforcast

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by C.wan_yo on 14-Dec-17.
 */
data class Weather(
        var query: WeatherQuery = WeatherQuery()
)

data class WeatherQuery(
        @SerializedName("count") var count: Int = 0,
        @SerializedName("created") var createdDateTime: String = "",
        var results: WeatherQueryResult = WeatherQueryResult()
)

data class WeatherQueryResult(
        var channel: WeatherResultChannel = WeatherResultChannel()
)

data class WeatherResultChannel(
        @SerializedName("title") var title: String = "",
        var item: WeatherChannelItem = WeatherChannelItem()
)

data class WeatherChannelItem(
        var condition: WeatherItemCondition = WeatherItemCondition(),
        var forecast: LinkedList<WeatherItemForecast> = LinkedList<WeatherItemForecast>()
)

data class WeatherItemCondition(
        @SerializedName("date") var date: String = "",
        @SerializedName("temp") var temp: Int = 0,
        @SerializedName("text") var text: String = ""
)

data class WeatherItemForecast(
        @SerializedName("date") var date: String = "",
        @SerializedName("high") var high: String = "",
        @SerializedName("low") var low: String = "",
        @SerializedName("text") var text: String = ""
)