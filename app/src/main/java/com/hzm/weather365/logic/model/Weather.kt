package com.hzm.weather365.logic.model

class Weather(val realtime: RealtimeResponse.Realtime, val hourly: HourlyResponse.Hourly,
              val daily: DailyResponse.Daily)