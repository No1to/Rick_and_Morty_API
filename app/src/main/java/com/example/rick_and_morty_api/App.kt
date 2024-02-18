package com.example.rick_and_morty_api

import android.app.Application
import com.example.rick_and_morty_api.di.networkModule
import com.example.rick_and_morty_api.di.repositoryModule
import com.example.rick_and_morty_api.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(networkModule, repositoryModule, viewModelModule)
        }
    }

}