package com.internetbrands.whattoeat.database.repository

import com.internetbrands.whattoeat.bean.Cook
import com.internetbrands.whattoeat.bean.DayCook
import com.internetbrands.whattoeat.database.dao.CookDao
import com.internetbrands.whattoeat.util.CookAlreadyExistException
import com.internetbrands.whattoeat.viewmodel.listener.ResponseListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

/**
Created by Wei Lai 2020/4/22
Description:
 */
@Singleton
class CookRepository @Inject constructor(var dao: CookDao) : BaseRepository() {
    fun getAllCook(listener: ResponseListener<Cook>): Job =
        execute(
            call = { dao.getAllCook() },
            callback = { listener.onGetList(it) }
        )

    fun insertCook(list: List<Cook>) {
        dao.insert(list)
    }

    fun insertCook(cook: Cook, listener: ResponseListener<Long>): Job =
        GlobalScope.launch (Dispatchers.Main){
            var resultCook = dao.getByName(cook.name)
            if (resultCook != null){
                listener.onError(CookAlreadyExistException())
            }else{
                var result = dao.insert(cook)
                listener.onSuccess(result)
            }
        }


    fun getCookByName(name:String,listener: ResponseListener<Cook>):Job =
        execute(
            call = {dao.getByName(name)},
            callback = {listener.onSuccess(it)}
        )

    fun update(list: List<Cook>):Job = execute(
        call = {dao.update(list)}
    )

    fun getAllMeat(listener: ResponseListener<Cook>):Job = execute(
        call = {dao.getAllCookByType(Cook.MEAT)},
        callback = {listener.onGetList(it)}
    )

    fun getAllNoMeat(listener: ResponseListener<Cook>):Job = execute(
        call = {dao.getAllCookotherType(Cook.MEAT)},
        callback = {listener.onGetList(it)}
    )
}