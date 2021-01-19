package com.internetbrands.whattoeat.ui.activity

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import android.view.MenuItem

/**
Created by Aloyise 2019/2/21
Description:
 */
abstract class BaseToolbarActivity:BaseActivity() {

    abstract fun getLayoutId():Int

    abstract fun getToolBar():Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initToolbar(getToolBar())
    }

    open fun initToolbar(toolbar: Toolbar){
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        var id = item?.itemId
        when(id){
            android.R.id.home->onBackPressed()
        }
        return true
    }
}