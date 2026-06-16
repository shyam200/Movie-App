package com.example.movieapp.presentation.di

import com.example.movieapp.domain.usecases.GetMoviesUsecase
import com.example.movieapp.domain.usecases.UpdateMoviesUsecase
import com.example.movieapp.presentation.MovieViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class MovieModule {

    @MovieScope
    @Provides
    fun provideMovieViewModelFactory(getMoviesUsecase: GetMoviesUsecase, updateMoviesUsecase: UpdateMoviesUsecase) : MovieViewModelFactory {
        return MovieViewModelFactory(getMoviesUsecase, updateMoviesUsecase)
    }
}