package com.example.movieapp.data.datasourceImpl

import com.example.movieapp.data.api.TMDBService
import com.example.movieapp.data.datasource.MovieRemoteDataSource
import com.example.movieapp.data.model.MovieList
import retrofit2.Response

class MovieRemoteDataSourceImpl(private val service: TMDBService, private val apiKey: String) :
    MovieRemoteDataSource {
    override suspend fun getMovies(): Response<MovieList> =
        service.getPopularMovies(apiKey)
}