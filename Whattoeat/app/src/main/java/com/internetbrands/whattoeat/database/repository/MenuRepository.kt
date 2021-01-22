package com.internetbrands.whattoeat.database.repository

import com.internetbrands.whattoeat.bean.DayCook
import com.internetbrands.whattoeat.bean.DayMenu
import com.internetbrands.whattoeat.database.dao.MenuDao
import com.internetbrands.whattoeat.viewmodel.listener.ResponseListener
import kotlinx.coroutines.Job
import javax.inject.Inject

/**
Created by Wei Lai 2020/5/14
Description:
 */
class MenuRepository @Inject constructor(var dao: MenuDao) : BaseRepository() {

    fun insertDayMenu(dayMenu: DayMenu): Int = dao.insert(dayMenu).toInt()

    fun updateDayMenu(dayMenu: DayMenu) = dao.update(dayMenu)

    fun getDayMenuList(listener: ResponseListener<DayMenu>): Job =
        execute(
            call = { dao.getDayMenuList() },
            callback = { listener.onGetList(it) }
        )

    fun insertDayCook(list: List<DayCook>): Job = execute(
        call = { dao.insert(list) }
    )

    fun getTodayMenu(today: Int, listener: ResponseListener<DayMenu>): Job =
        execute(
            call = { dao.findDayMenu(today) },
            callback = {listener.onSuccess(it)},
            handler = {e->listener.onError(e)}
            )

    fun getDayCookListByCook(cookId:Int,listener: ResponseListener<DayCook>):Job =
        execute(
            call = {dao.findDayCookListByCook(cookId)},
            callback = {listener.onGetList(it)},
            handler = {e->listener.onError(e)}
        )

}