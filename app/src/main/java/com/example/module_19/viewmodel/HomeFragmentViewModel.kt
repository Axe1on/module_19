package com.example.module_19.viewmodel

import androidx.lifecycle.ViewModel
import com.example.module_19.App
import com.example.module_19.data.Entity.Film
import com.example.module_19.domain.Interactor
import com.example.module_19.view.SingleLiveEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeFragmentViewModel : ViewModel() {
    //Инициализируем интерактор
    @Inject
    lateinit var interactor: Interactor

    //val filmsListLiveData: LiveData<List<Film>>
    val showProgressBar: Channel<Boolean>
    val showErrorToast = SingleLiveEvent<Unit>()
    val filmsListData: Flow<List<Film>>

    init {
        App.instance.dagger.inject(this)
        filmsListData = interactor.getFilmsFromDB()
        showProgressBar = interactor.progressBarState
        getFilms()
    }

    fun getFilms() {
        interactor.getFilmsFromApi(1)
    }

}