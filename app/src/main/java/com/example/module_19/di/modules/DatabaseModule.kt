package com.example.module_19.di.modules

import android.content.Context
import androidx.room.Room
import com.example.module_19.data.MainRepository
import com.example.module_19.data.dao.FilmDao
import com.example.module_19.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabaseHelper(context: Context) =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "film_db"
        ).build().filmDao()

    @Provides
    @Singleton
    fun provideRepository(filmDao: FilmDao) = MainRepository(filmDao)
}