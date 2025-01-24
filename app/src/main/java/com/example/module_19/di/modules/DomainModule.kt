package com.example.module_19.di.modules

import com.example.module_19.data.MainRepository
import com.example.module_19.data.TmdbApi
import com.example.module_19.domain.Interactor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {
    @Singleton
    @Provides
    fun provideInteractor(repository: MainRepository, tmdbApi: TmdbApi) =
        Interactor(repo = repository, retrofitService = tmdbApi)
}