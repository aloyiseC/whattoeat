package com.internetbrands.whattoeat.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.internetbrands.whattoeat.bean.Cook
import com.internetbrands.whattoeat.bean.DayCook
import com.internetbrands.whattoeat.bean.DayMenu
import com.internetbrands.whattoeat.database.repository.CookRepository
import com.internetbrands.whattoeat.database.repository.MenuRepository
import com.internetbrands.whattoeat.util.Utils
import com.internetbrands.whattoeat.viewmodel.listener.ResponseListener
import javax.inject.Inject

/**
Created by Wei Lai 2020/5/14
Description:
 */
class AddMenuViewModel@Inject constructor(cookRepository: CookRepository,menuRepository: MenuRepository):CookMenuViewModel(cookRepository,menuRepository) {

    var meatCookList = MutableLiveData<List<Cook>>()
    var notMeatCookList = MutableLiveData<List<Cook>>()

    fun meatCookList():LiveData<List<Cook>> = meatCookList
    fun notMeatCookList():LiveData<List<Cook>> = notMeatCookList

    fun getMeatCookList(){
        cookRepository.getAllMeat(
            object:ResponseListener<Cook>{
                override fun onGetList(list: List<Cook>) {
                    meatCookList.value = list
                }
            }
        )
    }
    fun getNoMeatCookList(){
        cookRepository.getAllNoMeat(
            object:ResponseListener<Cook>{
                override fun onGetList(list: List<Cook>) {
                    notMeatCookList.value = list
                }
            }
        )
    }

   fun submitMenu(list:List<Cook>,today:Int){
       var names = Utils.setNames(list)
       var dmenuId = menuRepository.insertDayMenu(DayMenu(today,names))
       var dayCookList:ArrayList<DayCook> = ArrayList()
       for(cook in list){
           dayCookList.add(DayCook(cook.cid,dmenuId,today))
       }
       cookRepository.update(list)
       menuRepository.insertDayCook(dayCookList)

       success.value = true
   }
}