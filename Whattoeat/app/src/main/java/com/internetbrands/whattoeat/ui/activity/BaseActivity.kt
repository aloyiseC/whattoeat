package com.internetbrands.whattoeat.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjection

/**
Created by Wei Lai 2020/4/22
Description:
 */
open class BaseActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
    }
}