package com.hzm.weather365.logic

import android.util.Log
import androidx.lifecycle.liveData
import com.hzm.weather365.logic.dao.PlaceDao
import com.hzm.weather365.logic.model.Place
import com.hzm.weather365.logic.model.Weather
import com.hzm.weather365.logic.network.Weather365Network
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import java.lang.Exception
import java.lang.RuntimeException
import kotlin.coroutines.CoroutineContext

object Repository {
    private fun <T> fire(context:CoroutineContext, block:suspend ()->Result<T>) =
        liveData<Result<T>> (context){
            val result = try {
                block()
            }catch (e:Exception){
                Result.failure<T>(e)
            }
            emit(result)
        }

    fun searchPlaces(query:String) = fire(Dispatchers.IO){
        Log.i("Place","开始获取place")
        val placeResponse = Weather365Network.searchPlace(query)
        if (placeResponse.status == "ok"){
            val places = placeResponse.places
//            Log.i("Place","获取place成功${places}")
            Result.success(places)
        } else {
//            Log.i("Place","获取place失败")
            Result.failure(RuntimeException("response tatus is ${placeResponse.status}"))
        }
    }

    fun refreshWeather(lng:String, lat:String) = fire(Dispatchers.IO){
        coroutineScope {
            val deferredRealtime = async {
                Weather365Network.getRealtimeWeather(lng,lat)
            }
            val deferredDaily = async {
                Weather365Network.getDailyWeather(lng,lat)
            }
            val deferredHourly = async {
                Weather365Network.getHourlyWeather(lng,lat)
            }
            val realtimeResponse = deferredRealtime.await()
            val dailyResponse = deferredDaily.await()
            val hourlyResponse = deferredHourly.await()
            if (realtimeResponse.status == "ok" && dailyResponse.status == "ok" && hourlyResponse.status == "ok"){
                val weather = Weather(realtimeResponse.result.realtime,hourlyResponse.result.hourly,
                    dailyResponse.result.daily)
                Log.i("Weather","Weather${weather}")
                Result.success(weather)
            }else{
                Log.i("Weather","获取天气失败")
                Result.failure(RuntimeException("realtime response tatus is ${realtimeResponse.status}"+
                "daily response tatus is ${dailyResponse.status}"))
            }
        }
    }

    fun savePlace(place:Place) = PlaceDao.savePlace(place)
    fun getSavedPlace() = PlaceDao.getSavedPlace()
    fun isPlaceSaved() = PlaceDao.isPlaceSaved()
}