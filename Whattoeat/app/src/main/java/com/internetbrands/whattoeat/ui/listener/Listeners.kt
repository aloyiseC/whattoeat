package com.internetbrands.whattoeat.ui.listener

import com.internetbrands.whattoeat.bean.Cook
import com.internetbrands.whattoeat.bean.DayMenu

/**
Created by Wei Lai 2020/5/18
Description:
 */
interface CookCheckListener {
    fun onCheck(cook: Cook)
}
interface OnDayMenuClickListener{
    fun onClick(dayMenu: DayMenu?)
}