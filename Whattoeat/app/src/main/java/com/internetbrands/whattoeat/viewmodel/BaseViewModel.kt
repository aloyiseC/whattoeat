package com.internetbrands.whattoeat.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job

/**
Created by Wei Lai 2020/4/22
Description:
 */
open class BaseViewModel : ViewModel() {
    var success: MutableLiveData<Boolean> = MutableLiveData()
    var error:MutableLiveData<Throwable> = MutableLiveData()

    fun success():LiveData<Boolean> = success
    fun error():LiveData<Throwable> = error

    var job: Job? = null;

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}