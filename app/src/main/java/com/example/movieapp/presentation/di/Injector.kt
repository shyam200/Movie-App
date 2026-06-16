package com.example.movieapp.presentation.di

interface Injector {
    fun createMovieSubComponent() : MovieSubcomponent
}