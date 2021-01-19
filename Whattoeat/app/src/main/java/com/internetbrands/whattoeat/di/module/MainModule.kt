package com.internetbrands.whattoeat.di.module

import com.internetbrands.whattoeat.bean.Cook
import com.internetbrands.whattoeat.bean.DayMenu
import com.internetbrands.whattoeat.ui.fragment.CookMenuFragment
import com.internetbrands.whattoeat.ui.fragment.WeekMenuFragment
import dagger.Module
import dagger.Provides

/**
Created by Wei Lai 2020/4/23
Description:
 */
@Module
class MainModule {
    @Provides
    fun getInitCookList():List<Cook>{
        var list:ArrayList<Cook> = ArrayList()
        list.add(Cook("海椒煎鸭子",Cook.MEAT))
        list.add(Cook("回锅肉",Cook.MEAT))
        list.add(Cook("炝炒莲白",Cook.VERG))
        list.add(Cook("麻婆豆腐",Cook.MEAT))
        list.add(Cook("红烧排骨",Cook.MEAT))
        list.add(Cook("红烧鸡翅",Cook.MEAT))
        list.add(Cook("青椒牛肉丝",Cook.MEAT))
        list.add(Cook("宫保鸡丁",Cook.MEAT))
        list.add(Cook("土豆红烧肉",Cook.MEAT))
        list.add(Cook("红枣小米粥",Cook.RISE))
        return list
    }

    @Provides
    fun provideDayMenu():DayMenu = DayMenu(20200512,"麻婆豆腐,炝炒莲白")

    @Provides
    fun provideCookMenu():CookMenuFragment = CookMenuFragment()

    @Provides
    fun provideWeekMenu():WeekMenuFragment = WeekMenuFragment()
}