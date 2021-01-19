package com.internetbrands.whattoeat.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.internetbrands.whattoeat.bean.Cook
import com.internetbrands.whattoeat.bean.DayMenu
import com.internetbrands.whattoeat.database.repository.CookRepository
import com.internetbrands.whattoeat.database.repository.MenuRepository
import com.internetbrands.whattoeat.viewmodel.listener.ResponseListener
import kotlinx.coroutines.Job
import javax.inject.Inject

/**
Created by Wei Lai 2020/4/22
Description:
 */
class MainViewModel @Inject constructor(var cookRepository: CookRepository,var menuRepository: MenuRepository) : BaseViewModel() {
    fun insertCookList(list: List<Cook>) {
        cookRepository.insertCook(list)
    }

    fun insertMenu(dayMenu: DayMenu){
        menuRepository.insertDayMenu(dayMenu)
    }
}