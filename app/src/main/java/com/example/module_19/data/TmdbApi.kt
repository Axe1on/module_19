package com.example.module_19.data

import com.example.module_19.data.Entity.TmdbResultsDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbApi {
    @GET("3/movie/popular")
    fun getFilms(
        @Query("api_key") apiKey:String,//наш ключ API, который мы получили.
        @Query("language") language:String,//язык, на котором будет сформирован список.
        @Query("page") page:Int,//страница списка, это задел для пагинации.
    ): Call<TmdbResultsDto>
}