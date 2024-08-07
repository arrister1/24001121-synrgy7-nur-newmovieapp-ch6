package com.example.movieapp

import android.app.Application
import com.example.movieapp.presentation.di.KoinModule.dataModule
import com.example.movieapp.presentation.di.KoinModule.networkModule
import com.example.movieapp.presentation.di.KoinModule.repositoryModule
import com.example.movieapp.presentation.di.KoinModule.useCaseModule
import com.example.movieapp.presentation.di.KoinModule.viewModelModule

import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@App)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    dataModule,
                    useCaseModule,
                    viewModelModule)
            )
        }
    }
}