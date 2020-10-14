package com.hzm.weather365

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : CheckPermissionsActivity() {
    //声明AMapLocationClient类对象，并初始化
    private val mLocationClient = AMapLocationClient(Weather365Application.context)
    private val mLocationOption = AMapLocationClientOption()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initOption()
        mLocationClient.setLocationOption(mLocationOption)
        //异步获取定位结果
        val mAMapLocationListener = AMapLocationListener { loc ->
            //void onLocationChanged(AMapLocation var1);
            if (null != loc && loc.errorCode == 0) {
                //解析定位结果
                main_text.text = Utils.getLocationStr(loc)
//                val longitude: Double = loc.getLongitude()
//                val latitude: Double = loc.getLatitude()
//                val location = LatLng(latitude, longitude)
//                changeLocation(location)
            } else {
                main_text.text = "定位失败"
            }
        }
        //设置定位回调监听
        mLocationClient.setLocationListener(mAMapLocationListener)
    }

    override fun onResume() {
        super.onResume()
        //启动定位
//        mLocationClient.startLocation()
//        main_text.text = "正在定位"
    }

    /*private fun changeLocation(location: LatLng) {
        if (locMarker == null) {
            locMarker = amap.addMarker(MarkerOptions().position(location))
        } else {
            locMarker.setPosition(location)
        }
        amap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15))
    }*/

    override fun onDestroy() {
        super.onDestroy()
        // 停止定位
        mLocationClient.stopLocation()
        mLocationClient.onDestroy()
    }

    private fun initOption() {
        // 设置定位模式为高精度模式
//        mLocationOption.locationMode = AMapLocationClientOption.AMapLocationMode.Hight_Accuracy
        // 设置是否需要显示地址信息
        mLocationOption.isNeedAddress = true
        /**
         * 设置是否优先返回GPS定位结果，如果30秒内GPS没有返回定位结果则进行网络定位
         * 注意：只有在高精度模式下的单次定位有效，其他方式无效
         */
//        mLocationOption.setGpsFirst(true)
        mLocationOption.isOnceLocation = true
        mLocationOption.isOnceLocationLatest = true
        // 设置是否开启缓存
        mLocationOption.isLocationCacheEnable = true
        // 设置发送定位请求的时间间隔,最小值为1000，如果小于1000，按照1000算
        mLocationOption.interval = 2000
        mLocationOption.httpTimeOut = 30000
    }

}