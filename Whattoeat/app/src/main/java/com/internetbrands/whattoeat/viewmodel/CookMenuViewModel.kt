package com.internetbrands.whattoeat.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.internetbrands.whattoeat.bean.Cook
import com.internetbrands.whattoeat.bean.CookDetail
import com.internetbrands.whattoeat.bean.DayCook
import com.internetbrands.whattoeat.database.repository.CookRepository
import com.internetbrands.whattoeat.database.repository.MenuRepository
import com.internetbrands.whattoeat.viewmodel.listener.ResponseListener
import javax.inject.Inject

/**
Created by Wei Lai 2020/5/12
Description:
 */
open class CookMenuViewModel@Inject constructor(var cookRepository: CookRepository,var menuRepository: MenuRepository):BaseViewModel() {
    var cooks: MutableLiveData<List<Cook>> = MutableLiveData()
    fun cooks(): LiveData<List<Cook>> = cooks

    var cookDetailResult = MutableLiveData<CookDetail>()
    fun cookDetailResult():LiveData<CookDetail> = cookDetailResult

    fun getAllCook() {
        job = cookRepository.getAllCook(
            object : ResponseListener<Cook> {
                override fun onGetList(list: List<Cook>) {
                    cooks.value = list
                }
            }
        )
    }

    fun getDayCookListByCook(cook:Cook){
        job = menuRepository.getDayCookListByCook(cook.cid,
        object:ResponseListener<DayCook>{
            override fun onGetList(list: List<DayCook>) {
                if(list.isNotEmpty()){
                    cookDetailResult.value = CookDetail(cook,list.size,list[0].date)
                }else{
                    cookDetailResult.value = CookDetail(cook,0,0)
                }
            }

            override fun onError(t: Throwable) {
                cookDetailResult.value = CookDetail(cook,0,0)
            }
        })
    }
}