package com.internetbrands.whattoeat.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.internetbrands.whattoeat.bean.Cook
import com.internetbrands.whattoeat.bean.DayCook
import com.internetbrands.whattoeat.bean.DayMenu
import com.internetbrands.whattoeat.database.dao.CookDao
import com.internetbrands.whattoeat.database.dao.MenuDao

/**
Created by Wei Lai 2020/4/22
Description:
 */
@Database(entities = [Cook::class,DayMenu::class,DayCook::class],version = 8,exportSchema = false)
abstract class AppDatabase :RoomDatabase(){
    abstract fun cookDao():CookDao
    abstract fun menuDao():MenuDao
}