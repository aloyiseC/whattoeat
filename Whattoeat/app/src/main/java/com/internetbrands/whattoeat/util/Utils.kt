package com.internetbrands.whattoeat.util

import com.internetbrands.whattoeat.bean.Cook
import com.internetbrands.whattoeat.bean.Week
import kotlinx.android.synthetic.main.activity_addmenu.*
import java.util.*
import kotlin.collections.ArrayList

/**
Created by Wei Lai 2020/5/12
Description:
 */
class Utils {
    companion object{
        fun getMondaySundayInt():Pair<Int,Int>{
            var cal = Calendar.getInstance()
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            var fist = cal.get(Calendar.YEAR)*10000+(cal.get(Calendar.MONTH)+1)*100+cal.get(Calendar.DATE);
            cal.set(Calendar.DATE, cal.get(Calendar.DATE) + 6);
            var second = cal.get(Calendar.YEAR)*10000+(cal.get(Calendar.MONTH)+1)*100+cal.get(Calendar.DATE);
            return Pair(fist,second)
        }

        fun getDayInt(cal:Calendar):Int = cal.get(Calendar.YEAR)*10000+(cal.get(Calendar.MONTH)+1)*100+cal.get(Calendar.DATE);

        fun setNames(list: List<Cook>):String{
            var names = ""
            for (cook in list) {
                if(names.isNotEmpty())
                    names += "," + cook.name
                else
                    names = cook.name
            }
            return names
        }

        fun <T> getRandomItem(list:List<T>):T{
            var size = list.size
            var index = Random().nextInt(size)
            return list[index]
        }
    }
}