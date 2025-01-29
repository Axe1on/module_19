package com.example.module_19

import android.app.Application
import com.example.module_19.di.AppComponent
import com.example.module_19.di.DaggerAppComponent
import com.example.module_19.di.modules.DatabaseModule
import com.example.module_19.di.modules.DomainModule
import com.example.module_19.di.modules.RemoteModule

class App : Application() {
    lateinit var dagger: AppComponent

    override fun onCreate() {
        super.onCreate()
        //Инициализируем экземпляр App, через который будем получать доступ к остальным переменным
        instance = this
        //Создаем компонент
        dagger = DaggerAppComponent.builder()
            .remoteModule(RemoteModule())
            .databaseModule(DatabaseModule())
            .domainModule(DomainModule(this))
            .build()
    }

    companion object {
        lateinit var instance: App
            private set
    }
}