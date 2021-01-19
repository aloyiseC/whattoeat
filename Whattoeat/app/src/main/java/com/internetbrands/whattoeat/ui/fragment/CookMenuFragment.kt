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
import com.internetbrands.whattoeat.bean.Cook
import com.internetbrands.whattoeat.bean.CookChangedEvent
import com.internetbrands.whattoeat.ui.activity.AddCookActivity
import com.internetbrands.whattoeat.ui.activity.CookDetailActivity
import com.internetbrands.whattoeat.ui.adapter.CookAdapter
import com.internetbrands.whattoeat.ui.listener.CookCheckListener
import com.internetbrands.whattoeat.util.injectViewModel
import com.internetbrands.whattoeat.util.newIntent
import com.internetbrands.whattoeat.util.newIntentForResult
import com.internetbrands.whattoeat.viewmodel.CookMenuViewModel
import com.internetbrands.whattoeat.viewmodel.factory.CookMenuViewModelFactory
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import javax.inject.Inject

/**
Created by Wei Lai 2020/5/12
Description:
 */
class CookMenuFragment:BaseFragment() {

    val REQUEST_CODE_ADDCOOK = 2020

    @Inject
    lateinit var factory:CookMenuViewModelFactory
    lateinit var viewModel: CookMenuViewModel
    lateinit var recyclerView: androidx.recyclerview.widget.RecyclerView
    lateinit var adapter: CookAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_cookmenu,container,false)
        return root
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        viewModel = injectViewModel(factory)
        EventBus.getDefault().register(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        viewModel.cooks().observe(this, Observer {
            refresh(it)
        })
        loadData()
    }

    private fun loadData(){
        viewModel.getAllCook()
    }

    private fun init() {
        recyclerView = root.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
        adapter = CookAdapter(context!!)
        recyclerView.adapter = adapter
        adapter.cookCheckListener = object : CookCheckListener{
            override fun onCheck(cook: Cook) {
                newIntent<CookDetailActivity>(cook)
            }
        }
    }

    fun refresh(list: List<Cook>){
        Log.i("step","size:"+list.size)
        adapter.list = list
        adapter.notifyDataSetChanged()
    }

    fun toAddCook(){
        newIntentForResult<AddCookActivity>(REQUEST_CODE_ADDCOOK)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        loadData()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun getCookChanged(evet:CookChangedEvent){
        loadData()
    }

    override fun onDetach() {
        super.onDetach()
        EventBus.getDefault().unregister(this)
    }
}