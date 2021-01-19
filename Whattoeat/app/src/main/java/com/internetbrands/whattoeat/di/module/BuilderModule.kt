package com.internetbrands.whattoeat.di.module

import com.internetbrands.whattoeat.ui.activity.AddCookActivity
import com.internetbrands.whattoeat.ui.activity.AddMenuActivity
import com.internetbrands.whattoeat.ui.activity.CookDetailActivity
import com.internetbrands.whattoeat.ui.activity.MainActivity
import com.internetbrands.whattoeat.ui.fragment.CookMenuFragment
import com.internetbrands.whattoeat.ui.fragment.WeekMenuFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
Created by Wei Lai 2020/4/23
Description:
 */
@Module
open abstract class BuilderModule {
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun bindMainModule():MainActivity
    @ContributesAndroidInjector(modules = [CookMenuModule::class])
    abstract fun bindCookMenu(): CookMenuFragment
    @ContributesAndroidInjector(modules = [AddCookModule::class])
    abstract fun bindAddCookModule():AddCookActivity
    @ContributesAndroidInjector(modules = [WeekMenuModule::class])
    abstract fun bindWeekMenu():WeekMenuFragment
    @ContributesAndroidInjector(modules = [AddMenuModule::class])
    abstract fun bindAddMenu():AddMenuActivity
    @ContributesAndroidInjector(modules=[CookDetailModule::class])
    abstract fun bindCookDetail():CookDetailActivity
}