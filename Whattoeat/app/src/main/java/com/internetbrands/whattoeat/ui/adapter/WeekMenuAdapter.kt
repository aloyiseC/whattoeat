package com.internetbrands.whattoeat.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.internetbrands.whattoeat.R
import com.internetbrands.whattoeat.bean.DayMenu
import com.internetbrands.whattoeat.bean.Week
import com.internetbrands.whattoeat.ui.listener.OnDayMenuClickListener
import com.internetbrands.whattoeat.util.Utils
import java.util.*
import kotlin.collections.ArrayList

/**
Created by Wei Lai 2020/5/13

Description:
 */
class WeekMenuAdapter(var context: Context) :
    RecyclerView.Adapter<WeekMenuAdapter.WeekMenuViewHolder>() {

    var menuList: List<DayMenu> = ArrayList()
    lateinit var onDayMenuClickListener: OnDayMenuClickListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeekMenuViewHolder {
        var item: View = LayoutInflater.from(context).inflate(R.layout.item_weekmenu, null)
        return WeekMenuViewHolder(item)
    }

    override fun getItemCount(): Int = menuList.size

    override fun onBindViewHolder(holder: WeekMenuViewHolder, position: Int) {
        var dayMenu: DayMenu? = menuList[position]

        holder.dayTv.setText(dayMenu?.date.toString())
        holder.namesTv.setText(dayMenu?.cookNames)

        if(isToday(dayMenu?.date)){
            holder.dayTv.setTextColor(context.getColor(R.color.color_f44336_80))
            holder.namesTv.setTextColor(context.getColor(R.color.color_f44336_80))
        }else {
            holder.dayTv.setTextColor(context.getColor(R.color.black))
            holder.namesTv.setTextColor(context.getColor(R.color.black))
        }
        holder.layout.setOnClickListener {
            if(onDayMenuClickListener != null){
                onDayMenuClickListener.onClick(dayMenu)
            }
        }
    }

    private fun isToday(date: Int?): Boolean {
        var cal = Calendar.getInstance()
        return Utils.getDayInt(cal) == date
    }

    class WeekMenuViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        var layout = item
        var dayTv: TextView = item.findViewById(R.id.dayTv)
        var namesTv: TextView = item.findViewById(R.id.namesTv)
    }
}