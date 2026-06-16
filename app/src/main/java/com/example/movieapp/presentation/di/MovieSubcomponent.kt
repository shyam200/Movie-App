package com.example.movieapp.presentation.di

import com.example.movieapp.presentation.MainActivity
import dagger.Subcomponent

@MovieScope
@Subcomponent(modules = [MovieModule :: class])
interface MovieSubcomponent {

    fun inject(movieActivity: MainActivity)

    @Subcomponent.Factory
    interface Factory{
        fun create() : MovieSubcomponent
    }
}