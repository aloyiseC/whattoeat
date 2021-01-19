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

/**
Created by Wei Lai 2020/5/13

Description:
 */
class WeekMenuAdapter(var context: Context, var week: Week) :
    RecyclerView.Adapter<WeekMenuAdapter.WeekMenuViewHolder>() {

    val days: Array<String>
    var menuList: List<DayMenu> = ArrayList()
    lateinit var onDayMenuClickListener: OnDayMenuClickListener

    init {
        days = context.resources.getStringArray(R.array.week_day)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeekMenuViewHolder {
        var item: View = LayoutInflater.from(context).inflate(R.layout.item_weekmenu, null)
        return WeekMenuViewHolder(item)
    }

    override fun getItemCount(): Int = 7

    override fun onBindViewHolder(holder: WeekMenuViewHolder, position: Int) {
        var day = days[position]
        var dayMenu: DayMenu? = getDayMenu(position)

        holder.dayTv.setText(day)
        holder.namesTv.setText(dayMenu?.cookNames)

        if(week.isToday(position)){
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

    class WeekMenuViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        var layout = item
        var dayTv: TextView = item.findViewById(R.id.dayTv)
        var namesTv: TextView = item.findViewById(R.id.namesTv)
    }

    fun getDayMenu(position: Int): DayMenu? {
        var dayInt = week.week[position]
        var result: DayMenu? = null
        for (dayMenu in menuList) {
            if (dayMenu.date == dayInt) {
                result = dayMenu
                break
            }
        }
        return result
    }
}