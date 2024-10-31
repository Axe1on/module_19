package com.example.module_19.domain

import com.example.module_19.data.MainRepository

class Interactor(val repo: MainRepository) {
    fun getFilmDB():List<Film> = repo.filmsDataBase
}