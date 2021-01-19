package com.internetbrands.whattoeat.bean

import com.internetbrands.whattoeat.util.Utils
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

/**
Created by Wei Lai 2020/5/12
Description:
 */
data class MenuDay (var dayMenu: DayMenu,var list:List<DayCook>):Serializable
data class Week(var today:Int,var week:List<Int>):Serializable{
    fun getMonday():Int = week[0]
    fun getSunday():Int = week[6]
    fun isToday(position:Int):Boolean = today == week[position]

    companion object{
        fun getThisWeek():Week{
            var cal = Calendar.getInstance()
            var todayInt = Utils.getDayInt(cal)
            var weekIntList:ArrayList<Int> = ArrayList()
            var i = 0
            while (i <= 6){
                var cal = Calendar.getInstance()
                cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                cal.set(Calendar.DATE, cal.get(Calendar.DATE) + i);
                weekIntList.add(Utils.getDayInt(cal))
                i++
            }
            return Week(todayInt,weekIntList)
        }
    }
}

data class CookDetail(var cook: Cook,var count:Int,var lastCook:Int){
    fun getName():String = cook.name
}