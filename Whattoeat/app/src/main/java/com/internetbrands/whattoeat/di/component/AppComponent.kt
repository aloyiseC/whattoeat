package com.internetbrands.whattoeat.di.component

import com.internetbrands.whattoeat.App
import com.internetbrands.whattoeat.di.module.BuilderModule
import com.internetbrands.whattoeat.di.module.DaoModule
import com.internetbrands.whattoeat.di.module.ToolModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
Created by Wei Lai 2020/4/22
Description:
 */
@Singleton
@Component(modules = [AndroidSupportInjectionModule::class,ToolModule::class,DaoModule::class,BuilderModule::class])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: App): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}