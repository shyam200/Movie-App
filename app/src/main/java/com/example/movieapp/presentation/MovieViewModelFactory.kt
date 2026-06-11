package com.example.movieapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.domain.usecases.GetMoviesUsecase
import com.example.movieapp.domain.usecases.UpdateMoviesUsecase

class MovieViewModelFactory(
    private val getMoviesUsecase: GetMoviesUsecase,
    private val updateMoviesUsecase: UpdateMoviesUsecase
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
       return MovieViewModel(getMoviesUsecase, updateMoviesUsecase) as T
    }
}