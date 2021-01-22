package com.internetbrands.whattoeat.ui.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.internetbrands.whattoeat.R
import com.internetbrands.whattoeat.bean.CookChangedEvent
import com.internetbrands.whattoeat.bean.DayMenu
import com.internetbrands.whattoeat.bean.Week
import com.internetbrands.whattoeat.ui.activity.AddMenuActivity
import com.internetbrands.whattoeat.ui.adapter.WeekMenuAdapter
import com.internetbrands.whattoeat.ui.listener.OnDayMenuClickListener
import com.internetbrands.whattoeat.util.*
import com.internetbrands.whattoeat.viewmodel.WeekMenuViewModel
import com.internetbrands.whattoeat.viewmodel.factory.WeekMenuViewModelFactory
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

/**
Created by Wei Lai 2020/5/13
Description:
 */
class WeekMenuFragment : BaseFragment() {

    val REQUEST_CODE_ADD_MENU = 2021

    @Inject
    lateinit var factory:WeekMenuViewModelFactory
    lateinit var viewModel:WeekMenuViewModel
    lateinit var recyclerView: androidx.recyclerview.widget.RecyclerView
    lateinit var adapter: WeekMenuAdapter

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        viewModel = injectViewModel(factory)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_weekmenu,container,false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        viewModel.menu().observe(this, Observer {
            refresh(it)
        })
        loadData()
    }

    private fun init() {
        recyclerView = root.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
        adapter = WeekMenuAdapter(context!!)
        adapter.onDayMenuClickListener = object : OnDayMenuClickListener {
            override fun onClick(dayMenu: DayMenu?) {
                if(dayMenu != null){

                }
            }
        }
        recyclerView.adapter = adapter
    }

    private fun loadData(){
        viewModel.getMenuList()
    }

    private fun refresh(list:List<DayMenu>){
        adapter.menuList = list
        adapter.notifyDataSetChanged()
    }

    fun toAddMenu(){
        newIntentForResult<AddMenuActivity>(REQUEST_CODE_ADD_MENU)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        loadData()
        EventBus.getDefault().post(CookChangedEvent())
    }
}