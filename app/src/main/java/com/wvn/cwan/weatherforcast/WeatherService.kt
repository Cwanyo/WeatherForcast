package com.wvn.cwan.weatherforcast

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by C.wan_yo on 14-Dec-17.
 */
interface WeatherService {

    @GET("v1/public/yql")
    fun getWeather(@Query("q") query: String, @Query("format") format: String): Call<Weather>

}