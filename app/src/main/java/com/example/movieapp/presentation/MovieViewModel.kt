package com.example.movieapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.movieapp.domain.usecases.GetMoviesUsecase
import com.example.movieapp.domain.usecases.UpdateMoviesUsecase

class MovieViewModel(
    private val getMoviesUsecase: GetMoviesUsecase,
    private val updateMoviesUsecase: UpdateMoviesUsecase) : ViewModel(){

    fun getMovies() = liveData {
        val movieList = getMoviesUsecase.execute()
        emit(movieList)
    }

    fun updateMovieUseCase() = liveData {
        val movieList = updateMoviesUsecase.execute()
        emit(movieList)
    }
}