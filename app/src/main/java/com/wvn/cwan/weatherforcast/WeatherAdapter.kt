package com.wvn.cwan.weatherforcast

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.row_lv_weather.view.*
import java.util.*

/**
 * Created by C.wan_yo on 14-Dec-17.
 */
class WeatherAdapter: BaseAdapter {

    var context: Context? = null
    var forecastList = LinkedList<WeatherItemForecast>()

    constructor(context: Context?, forecastList: LinkedList<WeatherItemForecast>) : super() {
        this.context = context
        this.forecastList = forecastList
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val layoutInflater = LayoutInflater.from(context)
        var row = layoutInflater.inflate(R.layout.row_lv_weather, null)

        var currForecase = this.forecastList.get(p0)

        row.r_tv_date.text = currForecase.date
        row.r_tv_hightemp.text = currForecase.high.toString()
        row.r_tv_lowtemp.text = currForecase.low.toString()
        row.r_tv_text.text = currForecase.text

        return row
    }

    override fun getItem(p0: Int): Any {
        return this.forecastList.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return this.forecastList.size
    }

}