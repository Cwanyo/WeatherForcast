package com.wvn.cwan.weatherforcast

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

data class fc(var date: String, var high: Int, var log: Int, var text: String)

class MainActivity : AppCompatActivity() {

    val url = "https://query.yahooapis.com"

    var forecastList = LinkedList<WeatherItemForecast>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resetValue()

        btn_search.setOnClickListener { searchWeather() }

        lv_weather.adapter = WeatherAdapter(applicationContext, this.forecastList)
    }

    private fun resetValue() {
        tv_date.text = ""
        tv_temp.text = ""
        tv_text.text = ""

        forecastList.clear()
    }

    private fun searchWeather() {
        //reset value
        resetValue()

        val city = et_cityname.text.toString()
        val query = "select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"$city\")"
        val format = "json"

        var retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        var weatherService = retrofit.create(WeatherService::class.java)
        var call = weatherService.getWeather(query, format)

        call.enqueue(object : Callback<Weather> {
            override fun onResponse(call: Call<Weather>?, response: Response<Weather>?) {
                Toast.makeText(applicationContext, "DONE", Toast.LENGTH_LONG).show()
                println("done")

                val weather = response?.body() as? Weather

                if(weather == null){
                    Toast.makeText(applicationContext, "Empty OR Error", Toast.LENGTH_LONG).show()
                    println("empty or error")
                    return
                }

                var tempF = weather?.query.results.channel.item.condition.temp

                //set text view
                tv_date?.text = weather.query.results.channel.item.condition.date
                tv_temp?.text = "${(tempF - 32) * 5 / 9} Celsius" // convert F to C
                tv_text?.text = weather.query.results.channel.item.condition.text

                val array = weather.query.results.channel.item.forecast
                for (i in array) {
                    var high = (i.high.toInt() - 32) * 5 / 9
                    var low = (i.low.toInt() - 32) * 5 / 9
                    forecastList.add(WeatherItemForecast(i.date, high.toString(), low.toString(), i.text))
                }
            }

            override fun onFailure(call: Call<Weather>?, t: Throwable?) {
                Toast.makeText(applicationContext, "ERROR", Toast.LENGTH_LONG).show()
                println("error")
            }

        })


    }
}
