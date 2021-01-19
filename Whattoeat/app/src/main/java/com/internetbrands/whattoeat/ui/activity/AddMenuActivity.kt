package com.internetbrands.whattoeat.ui.activity

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import com.internetbrands.whattoeat.R
import com.internetbrands.whattoeat.bean.Cook
import com.internetbrands.whattoeat.bean.DayMenu
import com.internetbrands.whattoeat.ui.adapter.CookAdapter
import com.internetbrands.whattoeat.util.Constants
import com.internetbrands.whattoeat.util.Utils
import com.internetbrands.whattoeat.util.injectViewModel
import com.internetbrands.whattoeat.viewmodel.AddMenuViewModel
import com.internetbrands.whattoeat.viewmodel.factory.AddMenuViewModelFactory
import kotlinx.android.synthetic.main.activity_addmenu.*
import javax.inject.Inject

/**
Created by Wei Lai 2020/5/14
Description:
 */
class AddMenuActivity : BaseToolbarActivity() {

    @Inject
    lateinit var factory: AddMenuViewModelFactory
    lateinit var viewModel: AddMenuViewModel
    private lateinit var adapter: CookAdapter
    private lateinit var dayMenu: DayMenu
    var dayInt:Int = 0

    override fun getLayoutId(): Int = R.layout.activity_addmenu
    override fun getToolBar(): Toolbar = addmenuToolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = injectViewModel(factory)
        init()
        viewModel.cooks().observe(this, Observer {
            refresh(it)
        })
        viewModel.success().observe(this, Observer { onSuccess(it) })
        loadData()
        dayInt = intent.getIntExtra(Constants.INDEX_KEY,0)
        submitBtn.setOnClickListener {
            viewModel.submitMenu(adapter.selectedList,dayInt)
        }
        viewModel.meatCookList().observe(this, Observer {
            adapter.meatCookList = it
        })
        viewModel.notMeatCookList().observe(this, Observer {
            adapter.noMeatCookList = it
        })
        viewModel.getMeatCookList()
        viewModel.getNoMeatCookList()
        randomBtn.setOnClickListener {
            adapter.getRandomCook()
        }


    }

    private fun init() {
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        adapter = CookAdapter(this)
        recyclerView.adapter = adapter
        adapter.selectedList().observe(this, Observer {
             setNames(it)
        })
    }

    fun loadData() {
        viewModel.getAllCook()
    }

    private fun refresh(list: List<Cook>) {
        adapter.list = list
        adapter.notifyDataSetChanged()
    }

    private fun setNames(list: List<Cook>){
        namesTv.text = Utils.setNames(list)
    }

    private fun onSuccess(value:Boolean){
        setResult(Activity.RESULT_OK)
        finish()
    }

}