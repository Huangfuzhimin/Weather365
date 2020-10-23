package com.hzm.weather365.logic.model

import java.util.*

class HourlyResponse(val status:String, val result: Result) {
    data class Result(val hourly: Hourly)
    data class Hourly(val description:String, val precipitation: List<Precipitation>,
                        val temperature:List<Temperature>,val skycon:List<Skycon>)

    data class Precipitation(val datetime: Date,val value:Float)
    data class Temperature(val datetime: Date,val value:Float)
    data class Skycon(val date:Date, val value:String)

}
