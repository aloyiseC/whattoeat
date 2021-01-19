package com.internetbrands.whattoeat.viewmodel

import com.internetbrands.whattoeat.bean.Cook
import com.internetbrands.whattoeat.database.repository.CookRepository
import com.internetbrands.whattoeat.viewmodel.listener.ResponseListener
import javax.inject.Inject

/**
Created by Wei Lai 2020/5/13
Description:
 */
class AddCookViewModel@Inject constructor(var cookRepository: CookRepository):BaseViewModel() {

    fun addCook(name:String,type:Int){
        cookRepository.insertCook(Cook(name,type),object : ResponseListener<Long>{
            override fun onSuccess(t: Long) {
                success.value = true
            }
            override fun onError(t: Throwable) {
                error.value = t
            }
        })
    }

}