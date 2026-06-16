package com.example.movieapp.presentation.di

import com.example.movieapp.domain.repository.MovieRepository
import com.example.movieapp.domain.usecases.GetMoviesUsecase
import com.example.movieapp.domain.usecases.UpdateMoviesUsecase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UsecaseModule {

    @Provides
    fun getMovieUsecase(repository: MovieRepository): GetMoviesUsecase {
        return GetMoviesUsecase(repository)
    }

    @Provides
    fun updateMovieUsecase(repository: MovieRepository): UpdateMoviesUsecase {
        return UpdateMoviesUsecase(repository)
    }
}