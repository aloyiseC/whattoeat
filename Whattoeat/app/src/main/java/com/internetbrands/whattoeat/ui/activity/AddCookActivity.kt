package com.internetbrands.whattoeat.ui.activity

import android.app.Activity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import com.internetbrands.whattoeat.R
import com.internetbrands.whattoeat.util.CookAlreadyExistException
import com.internetbrands.whattoeat.util.injectViewModel
import com.internetbrands.whattoeat.util.showToast
import com.internetbrands.whattoeat.viewmodel.AddCookViewModel
import com.internetbrands.whattoeat.viewmodel.factory.AddCookViewModelFactory
import kotlinx.android.synthetic.main.activity_addcook.*
import java.lang.Exception
import javax.inject.Inject

/**
Created by Wei Lai 2020/5/13
Description:
 */
class AddCookActivity:BaseToolbarActivity() {

    @Inject
    lateinit var factory:AddCookViewModelFactory
    lateinit var viewModel:AddCookViewModel

    override fun getLayoutId(): Int = R.layout.activity_addcook
    override fun getToolBar(): Toolbar = addcookToolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        typeSp.adapter = ArrayAdapter(this,R.layout.spinner_item_cook_type
            ,resources.getStringArray(R.array.cook_type_array))

        viewModel = injectViewModel(factory)

        submitBtn.setOnClickListener {
            var name = namEt.text.toString()
            var type = typeSp.selectedItemPosition

            if(name.isNotEmpty()){
                viewModel.addCook(name,type)
            }
        }

        viewModel.success().observe(this, Observer{ setResult(Activity.RESULT_OK)
            finish()})
        viewModel.error().observe(this, Observer {
            onError(it)
        })

    }

    fun onError(t:Throwable){
        if(t is CookAlreadyExistException){
            showToast("菜品已经存在")
        }
    }
}