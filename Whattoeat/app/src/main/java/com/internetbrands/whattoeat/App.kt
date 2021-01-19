package com.internetbrands.whattoeat

import android.app.Activity
import android.app.Application
import android.app.Fragment
import com.internetbrands.whattoeat.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasFragmentInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
Created by Wei Lai 2020/4/22
Description:
 */

open class App :Application(),HasActivityInjector,HasSupportFragmentInjector{

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>
    @Inject
    lateinit var dispatchingFragmentInjector: DispatchingAndroidInjector<androidx.fragment.app.Fragment>

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector

    override fun supportFragmentInjector(): AndroidInjector<androidx.fragment.app.Fragment> = dispatchingFragmentInjector

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder().application(this).build().inject(this)
    }
}