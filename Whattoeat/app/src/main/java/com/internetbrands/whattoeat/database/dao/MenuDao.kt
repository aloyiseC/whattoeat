package com.internetbrands.whattoeat.database.dao

import androidx.room.*
import com.internetbrands.whattoeat.bean.DayCook
import com.internetbrands.whattoeat.bean.DayMenu

/**
Created by Wei Lai 2020/5/14
Description:
 */
@Dao
interface MenuDao {
    @Insert
    fun insert(dayMenu: DayMenu):Long

    @Update
    fun update(dayMenu: DayMenu)

    @Query("select * from daymenu where date >= :mondayInt and date <= :sundayInt")
    suspend fun getWeekMenu(mondayInt:Int,sundayInt:Int):List<DayMenu>

    @Insert
    suspend fun insert(list:List<DayCook>)

    @Query("select * from daymenu where date = :date")
    suspend fun findDayMenu(date:Int):DayMenu

    @Query("select * from daycook where dmenuId = :dmenuId")
    suspend fun findDayCookListByDayMenu(dmenuId:Int):List<DayCook>

    @Query("select * from daycook where cookId =:cookId order by date")
    suspend fun findDayCookListByCook(cookId:Int):List<DayCook>

    @Delete
    suspend fun deleteDayMenu(dayMenu: DayMenu)

    @Delete
    suspend fun deleteDayCook(list: List<DayCook>)

}