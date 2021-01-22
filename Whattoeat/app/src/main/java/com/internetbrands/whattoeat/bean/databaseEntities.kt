package com.internetbrands.whattoeat.bean

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.io.Serializable
import java.util.*

/**
Created by Wei Lai 2020/4/22
Description:
 */
@Entity
class Cook :Serializable{
    @PrimaryKey(autoGenerate = true)
    var cid: Int = 0
    var name: String
    var type: Int = 0

    constructor(name: String, type: Int) {
        this.name = name
        this.type = type
    }

    companion object {
        val MEAT = 0
        val VERG = 1
        val RISE = 2
        val SOUP = 3
    }

    override fun equals(other: Any?): Boolean {
        if(other is Cook){
            return other.cid == cid
        }else
            return super.equals(other)
    }
}

//每天的菜单
@Entity
class DayMenu :Serializable{
    @PrimaryKey(autoGenerate = true)
    var dmenuId: Int = 0
    var date: Int = 0;
    var cookNames: String? = null

    constructor(date: Int, cookNames: String?) {
        this.date = date
        this.cookNames = cookNames
    }
}

@Entity
class DayCook {
    @PrimaryKey(autoGenerate = true)
    var dcookId: Int = 0
    var cookId: Int = 0
    var dmenuId: Int = 0
    var date:Int = 0

    constructor(cookId: Int, dmenuId: Int, date: Int) {
        this.cookId = cookId
        this.dmenuId = dmenuId
        this.date = date
    }
}