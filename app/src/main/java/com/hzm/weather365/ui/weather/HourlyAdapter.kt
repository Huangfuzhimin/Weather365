package com.hzm.weather365.ui.weather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hzm.weather365.R
import java.text.SimpleDateFormat
import java.util.*

class HourlyAdapter(val hourlyList:List<HourlyItemData>):RecyclerView.Adapter<HourlyAdapter.ViewHolder>() {
    data class HourlyItemData(val hourlyTime:Date,val hourlyIcon:String,val hourlyTem:Float)

    inner class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val hourlyTime:TextView = itemView.findViewById(R.id.hourlytimeInfo)
        val hourlyIcon:ImageView = itemView.findViewById(R.id.hourlySkyIcon)
        val hourlyTem:TextView = itemView.findViewById(R.id.hourlyTemperature)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.hourly_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val hourlyInfo = hourlyList[position]
        val simpleDateFormat = SimpleDateFormat("aa hh 时", Locale.CHINESE)
        holder.hourlyTime.text = simpleDateFormat.format(hourlyInfo.hourlyTime)
        val sky = com.hzm.weather365.logic.model.geSky(hourlyInfo.hourlyIcon)
        holder.hourlyIcon.setImageResource(sky.icon)
        holder.hourlyTem.text = "${hourlyInfo.hourlyTem.toInt()}°"
    }

    override fun getItemCount(): Int {
        return hourlyList.size
    }
}