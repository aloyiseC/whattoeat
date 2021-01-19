package com.internetbrands.whattoeat.di.module

import com.internetbrands.whattoeat.database.AppDatabase
import com.internetbrands.whattoeat.database.dao.CookDao
import com.internetbrands.whattoeat.database.dao.MenuDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
Created by Wei Lai 2020/4/23
Description:
 */
@Module
class DaoModule {

    @Provides
    @Singleton
    fun provideCookDao(database: AppDatabase):CookDao = database.cookDao()

    @Provides
    @Singleton
    fun provideMenuDao(database: AppDatabase): MenuDao = database.menuDao()
}