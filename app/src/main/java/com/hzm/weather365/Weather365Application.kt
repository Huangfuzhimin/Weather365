package com.hzm.weather365

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class Weather365Application : Application(){
    companion object {
        const val TOKEN = "iizCmwfLrZoUfpxN"
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}