package com.example.module_19.di.modules

import android.content.Context
import com.example.module_19.data.MainRepository
import com.example.module_19.data.db.DatabaseHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabaseHelper(context: Context) = DatabaseHelper(context)

    @Provides
    @Singleton
    fun provideRepository(databaseHelper: DatabaseHelper) = MainRepository(databaseHelper)
}