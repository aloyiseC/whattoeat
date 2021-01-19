package com.internetbrands.whattoeat.di.module

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import androidx.room.RoomDatabase
import com.internetbrands.whattoeat.App
import com.internetbrands.whattoeat.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
Created by Wei Lai 2020/4/23
Description:
 */
@Module
class ToolModule {
    @Provides
    @Singleton
    fun provideAppDataBase(context: Context): AppDatabase
        = Room.databaseBuilder(context,AppDatabase::class.java,"data_base")
            .allowMainThreadQueries().setJournalMode(RoomDatabase.JournalMode.WRITE_AHEAD_LOGGING)
            .fallbackToDestructiveMigration()
            .build()


    @Provides
    @Singleton
    fun provideAppSharePreference(context: Context): SharedPreferences
        = context.getSharedPreferences("aloyise", Context.MODE_PRIVATE)


    @Provides
    @Singleton
    fun provideApplication(app:App):Context = app.applicationContext
}