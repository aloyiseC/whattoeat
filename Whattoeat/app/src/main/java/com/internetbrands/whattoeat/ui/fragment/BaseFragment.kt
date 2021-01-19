package com.internetbrands.whattoeat.ui.fragment

import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import dagger.android.support.AndroidSupportInjection
import org.greenrobot.eventbus.EventBus

/**
Created by Wei Lai 2020/5/12
Description:
 */
open class BaseFragment : Fragment() {

    lateinit var root: View

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }
}