package com.example.movieapp

import android.app.Application
import com.example.movieapp.presentation.di.AppComponent
import com.example.movieapp.presentation.di.AppModule
import com.example.movieapp.presentation.di.DaggerAppComponent
import com.example.movieapp.presentation.di.Injector
import com.example.movieapp.presentation.di.MovieSubcomponent
import com.example.movieapp.presentation.di.NetModule
import com.example.movieapp.presentation.di.RemoteDataModule

class App : Application(), Injector {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent =
            DaggerAppComponent.builder()
                .appModule(AppModule(applicationContext))
                .netModule(
                    NetModule(BuildConfig.BASE_URL)
                )
                .remoteDataModule((RemoteDataModule(BuildConfig.API_KEY)))
                .build()
    }

    override fun createMovieSubComponent(): MovieSubcomponent {
       return appComponent.movieSubComponent().create()
    }
}