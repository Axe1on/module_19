package com.example.module_19.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.module_19.App
import com.example.module_19.domain.Film
import com.example.module_19.domain.Interactor

class HomeFragmentViewModel : ViewModel(){
    val filmsListLifeData = MutableLiveData<List<Film>>()
    //Инициализируем интерактор
    private var interactor : Interactor = App.instance.interactor
    init {
        val film = interactor.getFilmDB()
        filmsListLifeData.postValue(film)
    }
}