package com.internetbrands.whattoeat.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.internetbrands.whattoeat.viewmodel.*
import javax.inject.Inject

/**
Created by Wei Lai 2020/4/23
Description:
 */
class MainViewModelFactory @Inject constructor(var viewModel:MainViewModel):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = viewModel as T
}

class CookMenuViewModelFactory @Inject constructor(var viewModel:CookMenuViewModel): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = viewModel as T

}

class AddCookViewModelFactory @Inject constructor(var viewModel:AddCookViewModel):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = viewModel as T
}

class WeekMenuViewModelFactory @Inject constructor(var viewModel:WeekMenuViewModel):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = viewModel as T
}

class AddMenuViewModelFactory @Inject constructor(var viewModel: AddMenuViewModel):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = viewModel as T
}

class CookDetailViewModelFactory @Inject constructor(var viewModel: CookDetailViewModel):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = viewModel as T
}
