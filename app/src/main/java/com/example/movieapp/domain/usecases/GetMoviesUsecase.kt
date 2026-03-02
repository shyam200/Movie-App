package com.example.movieapp.domain.usecases

import com.example.movieapp.data.model.Movie
import com.example.movieapp.domain.repository.MovieRepository

class GetMoviesUsecase(private val repository: MovieRepository) {
    suspend fun execute(): List<Movie>? = repository.getMovies()
}