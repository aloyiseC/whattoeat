package com.internetbrands.whattoeat.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.internetbrands.whattoeat.bean.Cook
import com.internetbrands.whattoeat.bean.CookDetail
import com.internetbrands.whattoeat.bean.DayCook
import com.internetbrands.whattoeat.database.repository.MenuRepository
import com.internetbrands.whattoeat.viewmodel.listener.ResponseListener
import javax.inject.Inject

/**
Created by Wei Lai 2020/5/20
Description:
 */
class CookDetailViewModel@Inject constructor(var menuRepository: MenuRepository):BaseViewModel() {

    var cookDetail = MutableLiveData<CookDetail>()
    fun cookDetail():LiveData<CookDetail> = cookDetail

    fun getCookDetail(cook: Cook){
        menuRepository.getDayCookListByCook(cook.cid,
            object :ResponseListener<DayCook>{
                override fun onGetList(list: List<DayCook>) {
                    cookDetail.value = CookDetail(cook,list.size,list[0].date)
                }

                override fun onError(t: Throwable) {
                    cookDetail.value = CookDetail(cook,0,0)
                }
            }
        )
    }
}