package com.hzm.weather365.logic.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {
    private const val BASE_URL = "https://api.caiyunapp.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)//指定所有Retrofit请求的根路径
        .addConverterFactory(GsonConverterFactory.create())//指定Retrofit在解析数据时使用的转换库
        .build()

    //获取serviceClass接口的方法一
    fun <T> create(serviceClass: Class<T>):T = retrofit.create(serviceClass)

    //获取serviceClass接口的方法二：泛型实化，两个条件（inline、reified)
    inline fun <reified T> create(): T = create(T::class.java)
}