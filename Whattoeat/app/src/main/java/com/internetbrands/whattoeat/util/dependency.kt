package com.internetbrands.whattoeat.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.internetbrands.whattoeat.ui.activity.BaseActivity
import com.internetbrands.whattoeat.ui.fragment.BaseFragment

/**
Created by Wei Lai 2020/4/23
Description:
 */
inline fun <reified T : ViewModel> BaseActivity.injectViewModel(factory: ViewModelProvider.Factory): T {
    return androidx.lifecycle.ViewModelProviders.of(this, factory).get(T::class.java)
}

inline fun <reified T:ViewModel> BaseFragment.injectViewModel(factory: ViewModelProvider.Factory):T{
    return ViewModelProviders.of(this,factory).get(T::class.java)
}