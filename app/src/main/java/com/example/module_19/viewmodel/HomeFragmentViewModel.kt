package com.example.module_19.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.module_19.App
import com.example.module_19.data.Entity.Film
import com.example.module_19.domain.Interactor
import com.example.module_19.view.SingleLiveEvent
import java.util.concurrent.Executors
import javax.inject.Inject

class HomeFragmentViewModel : ViewModel() {
    //Инициализируем интерактор
    @Inject
    lateinit var interactor: Interactor

    val filmsListLiveData: LiveData<List<Film>>
    val showProgressBar : MutableLiveData<Boolean> = MutableLiveData()
    val showErrorToast = SingleLiveEvent<Unit>()

    init {
        App.instance.dagger.inject(this)
        filmsListLiveData = interactor.getFilmsFromDB()
        getFilms()
    }

    fun getFilms() {
        showProgressBar.postValue(true)
        interactor.getFilmsFromApi(1, object : ApiCallback {
            override fun onSuccess() {
                showProgressBar.postValue(false)
            }

            override fun onFailure() {
               showProgressBar.postValue(false)
                showErrorToast.postValue(Unit) // Уведомляем о необходимости показать ошибку
            }
        })
    }

    interface ApiCallback {
        fun onSuccess()
        fun onFailure()
    }
}