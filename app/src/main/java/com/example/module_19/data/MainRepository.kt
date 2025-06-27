package com.example.module_19.data

import androidx.lifecycle.LiveData
import com.example.module_19.data.Entity.Film
import com.example.module_19.data.dao.FilmDao
import java.util.concurrent.Executors

class MainRepository(private val filmDao: FilmDao) {

    fun putToDb(films: List<Film>) {
        //Запросы в БД должны быть в отдельном потоке
        Executors.newSingleThreadExecutor().execute {
            filmDao.insertAll(films)
        }
    }
    fun getAllFromDB():LiveData<List<Film>>{
        return filmDao.getCachedFilms()
    }
}