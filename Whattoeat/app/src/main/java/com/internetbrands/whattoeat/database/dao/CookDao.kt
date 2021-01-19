package com.internetbrands.whattoeat.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.internetbrands.whattoeat.bean.Cook

/**
Created by Wei Lai 2020/4/22
Description:
 */
@Dao
interface CookDao {
    @Insert(onConflict = REPLACE)
    suspend fun insert(cook: Cook):Long

    @Query("select * from cook where type =:i")
    suspend fun getAllCookByType(i:Int): List<Cook>

    @Query("select * from cook where type <>:i")
    suspend fun getAllCookotherType(i:Int): List<Cook>

    @Query("select * from cook order by type")
    suspend fun getAllCook(): List<Cook>

    @Insert(onConflict = REPLACE)
    fun insert(list:List<Cook>)

    @Query("select * from cook where name=:name")
    suspend fun getByName(name:String):Cook

    @Update
    suspend fun update(list:List<Cook>)



}