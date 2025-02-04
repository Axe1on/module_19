package com.example.module_19.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.module_19.App
import com.example.module_19.domain.Film
import com.example.module_19.domain.Interactor
import javax.inject.Inject

class HomeFragmentViewModel : ViewModel() {
    val filmsListLiveData: MutableLiveData<List<Film>> = MutableLiveData()

    //Инициализируем интерактор
    @Inject
    lateinit var interactor: Interactor

    init {
        App.instance.dagger.inject(this)
        getFilms()
    }

    fun getFilms() {
        interactor.getFilmsFromApi(1, object : ApiCallback {
            override fun onSuccess(films: List<Film>) {
                filmsListLiveData.postValue(films)
            }

            override fun onFailure() {
                filmsListLiveData.postValue(interactor.getFilmsFromDB())
            }
        })
    }

    interface ApiCallback {
        fun onSuccess(films: List<Film>)
        fun onFailure()
    }
}