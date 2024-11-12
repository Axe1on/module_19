package com.example.module_19.domain

import com.example.module_19.data.API
import com.example.module_19.data.Entity.TmdbResultsDto
import com.example.module_19.data.MainRepository
import com.example.module_19.data.TmdbApi
import com.example.module_19.utils.Converter
import com.example.module_19.viewmodel.HomeFragmentViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Interactor(private val repo: MainRepository, private val retrofitService: TmdbApi) {
    //В конструктор мы будм передавать коллбэк из вьюмоделе, чтобы реагировать на то, когда фильмы будут получены
    //и страницу, котороую нужно загрузить (это для пагинации)
    fun getFilmsFromApi(page: Int, callback: HomeFragmentViewModel.ApiCallback) {
        retrofitService.getFilms(API.KEY, "ru-RU", page).enqueue(object : Callback<TmdbResultsDto> {
            override fun onResponse(call: Call<TmdbResultsDto>, response: Response<TmdbResultsDto>) {
                //При успехе мы вызываем метод передаем onSuccess и в этот коллбэк список фильмов
                callback.onSuccess(Converter.convertApiListToDtoList(response.body()?.tmdbFilms))
            }

            override fun onFailure(call: Call<TmdbResultsDto>, t: Throwable) {
                //В случае провала вызываем другой метод коллбека
                callback.onFailure()
            }
        })
    }
}