package com.internetbrands.whattoeat.viewmodel.listener

/**
Created by Aloyise 2019/3/8
Description:
 */
interface ResponseListener<T>{
    fun onGetList(list: List<T>){}
    fun onSuccess(t:T){}
    fun onError(t:Throwable){}
}