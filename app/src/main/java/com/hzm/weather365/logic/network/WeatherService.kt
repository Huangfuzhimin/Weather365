package com.hzm.weather365.logic.network

import com.hzm.weather365.Weather365Application
import com.hzm.weather365.logic.model.DailyResponse
import com.hzm.weather365.logic.model.HourlyResponse
import com.hzm.weather365.logic.model.RealtimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherService {
    //https://api.caiyunapp.com/v2.5/TAkhjf8d1nlSlspN/113.808087,22.670209/realtime.json
    //https://api.caiyunapp.com/v2.5/TAkhjf8d1nlSlspN/113.808087,22.670209/weather.json?alert=true
    @GET("v2.5/${Weather365Application.TOKEN}/{lng},{lat}/realtime.json")
    fun getRealtimeWeather(@Path("lng") lng:String, @Path("lat") lat:String): Call<RealtimeResponse>

    @GET("v2.5/${Weather365Application.TOKEN}/{lng},{lat}/daily.json")
    fun getDailyWeather(@Path("lng") lng:String, @Path("lat") lat:String): Call<DailyResponse>

//    https://api.caiyunapp.com/v2.5/TAkhjf8d1nlSlspN/121.6544,25.1552/hourly.json
    @GET("v2.5/${Weather365Application.TOKEN}/{lng},{lat}/hourly.json")
    fun getHourlyWeather(@Path("lng") lng:String, @Path("lat") lat:String): Call<HourlyResponse>
}