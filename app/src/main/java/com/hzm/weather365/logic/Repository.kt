package com.hzm.weather365.logic

import android.util.Log
import androidx.lifecycle.liveData
import com.hzm.weather365.logic.model.Place
import com.hzm.weather365.logic.network.Weather365Network
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import java.lang.RuntimeException

object Repository {
    fun searchPlaces(query:String) = liveData(Dispatchers.IO){
        Log.i("Place","开始获取place")
        val result = try {
            val placeResponse = Weather365Network.searchPlace(query)
            if (placeResponse.status == "ok"){
                val places = placeResponse.places
                Log.i("Place","获取place成功${places}")
                Result.success(places)
            } else {
                Log.i("Place","获取place失败")
                Result.failure(RuntimeException("response tatus is ${placeResponse.status}"))
            }
        }catch (e:Exception){
            Result.failure<List<Place>>(e)
        }
        emit(result)
    }
}