package com.hzm.weather365.logic.network

import com.hzm.weather365.Weather365Application
import com.hzm.weather365.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceService {

    //https://api.caiyunapp.com/v2.5/TAkhjf8d1nlSlspN/113.808087,22.670209/realtime.json
    //https://api.caiyunapp.com/v2.5/TAkhjf8d1nlSlspN/113.808087,22.670209/weather.json?alert=true
//    @GET("v2.5/${Weather365Application.TOKEN}/113.8077,22.6712/realtime.json")
    @GET("v2/place?token=${Weather365Application.TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query") query:String):Call<PlaceResponse>
}