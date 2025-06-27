package com.example.module_19.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.module_19.data.Entity.Film
import com.example.module_19.data.dao.FilmDao

@Database(entities = [Film::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun filmDao(): FilmDao
}