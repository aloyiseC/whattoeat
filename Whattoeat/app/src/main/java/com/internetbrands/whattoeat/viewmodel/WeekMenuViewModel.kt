package com.internetbrands.whattoeat.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.internetbrands.whattoeat.bean.DayMenu
import com.internetbrands.whattoeat.database.repository.MenuRepository
import com.internetbrands.whattoeat.util.DayMenuNoExistException
import com.internetbrands.whattoeat.viewmodel.listener.ResponseListener
import javax.inject.Inject

/**
Created by Wei Lai 2020/5/14
Description:
 */
class WeekMenuViewModel @Inject constructor(var repository: MenuRepository) : BaseViewModel() {

    var menuList: MutableLiveData<List<DayMenu>> = MutableLiveData()
    var dayMenu = MutableLiveData<DayMenu>()

    fun menu(): LiveData<List<DayMenu>> = menuList
    fun dayMenu(): LiveData<DayMenu> = dayMenu

    fun getMenuList() {
        repository.getDayMenuList(
            object : ResponseListener<DayMenu> {
                override fun onGetList(list: List<DayMenu>) {
                    menuList.value = list
                }
            }
        )
    }

    fun getDayMenu(today: Int) {
        repository.getTodayMenu(today,
            object : ResponseListener<DayMenu> {
                override fun onSuccess(t: DayMenu) {
                    dayMenu.value = t
                }

                override fun onError(t: Throwable) {
                    error.value = DayMenuNoExistException()
                }
            }
        )
    }

}