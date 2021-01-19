package com.internetbrands.whattoeat.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import com.internetbrands.whattoeat.R
import com.internetbrands.whattoeat.bean.Cook
import com.internetbrands.whattoeat.bean.CookDetail
import com.internetbrands.whattoeat.util.Constants
import com.internetbrands.whattoeat.util.injectViewModel
import com.internetbrands.whattoeat.viewmodel.CookDetailViewModel
import com.internetbrands.whattoeat.viewmodel.factory.CookDetailViewModelFactory
import kotlinx.android.synthetic.main.activity_cookdetail.*
import javax.inject.Inject

/**
Created by Wei Lai 2020/5/19
Description:
 */
class CookDetailActivity:BaseToolbarActivity() {

    @Inject
    lateinit var factory:CookDetailViewModelFactory
    lateinit var viewModel:CookDetailViewModel

    override fun getLayoutId(): Int = R.layout.activity_cookdetail

    override fun getToolBar(): Toolbar = cookDetailToolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = injectViewModel(factory)
        var cook: Cook = intent.getSerializableExtra(Constants.DATA_KEY) as Cook
        viewModel.cookDetail().observe(this, Observer {
            refresh(it)
        })
        viewModel.getCookDetail(cook)
    }

    private fun refresh(cookDetail:CookDetail){
        cookNameTv.text = cookDetail.getName()
        if(cookDetail.count == 0){
            cookCountTv.text = resources.getString(R.string.cookcount_zero_str)
            cookDateTv.visibility = View.GONE
        }else{
            cookCountTv.text = getString(R.string.cookcount_str,cookDetail.count)
            cookDateTv.text = cookDetail.lastCook.toString()
        }
    }
}