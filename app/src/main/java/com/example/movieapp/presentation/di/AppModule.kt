package com.example.movieapp.presentation.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = [MovieSubcomponent::class])
class AppModule(private val context: Context) {

    @Singleton
    @Provides
    fun getApplicationContext(): Context {
        return context.applicationContext
    }
}