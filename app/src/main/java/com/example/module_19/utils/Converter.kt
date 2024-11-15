package com.example.module_19.utils

import com.example.module_19.data.Entity.TmdbFilm
import com.example.module_19.domain.Film

object Converter {
    fun convertApiListToDtoList(list: List<TmdbFilm>?): List<Film> {
        val result = mutableListOf<Film>()
        list?.forEach {
            result.add(Film(
                title = it.title,
                poster = it.posterPath,
                description = it.overview,
                rating = it.voteAverage,
                isInFavorites = false
            ))
        }
        return result
    }
    //Просто получаем список с API и из его полей составляем новый список в нужном нам формате
}