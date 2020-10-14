package com.hzm.weather365.logic.dao

import android.content.Context
import androidx.core.content.edit
import com.google.gson.Gson
import com.hzm.weather365.Weather365Application
import com.hzm.weather365.logic.model.Place

object PlaceDao {
    fun savePlace(place: Place){
        sharedPreferences().edit{
            putString("place", Gson().toJson(place))
        }
    }

    fun getSavedPlace():Place{
        val placeJson = sharedPreferences().getString("place","")
        return Gson().fromJson(placeJson, Place::class.java)
    }

    fun isPlaceSaved() = sharedPreferences().contains("place")

    private fun sharedPreferences() = Weather365Application.context.getSharedPreferences("weater365", Context.MODE_PRIVATE)
}