package com.example.movieapp.data.repositoryImpl

import android.util.Log
import com.example.movieapp.data.datasource.MovieCacheDataSource
import com.example.movieapp.data.datasource.MovieLocalDataSource
import com.example.movieapp.data.datasource.MovieRemoteDataSource
import com.example.movieapp.data.model.Movie
import com.example.movieapp.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieCacheDataSource: MovieCacheDataSource
) : MovieRepository {
    override suspend fun getMovies(): List<Movie>? {
        val movieList = getMoviesFromCache()
        return movieList
    }

    override suspend fun updateMovies(): List<Movie> {
        val newListOfMovies = getMoviesFromAPI()
        movieLocalDataSource.clearAll()
        movieLocalDataSource.saveMoviesToDB(newListOfMovies)
        movieCacheDataSource.saveMoviesToCache(newListOfMovies)
        return newListOfMovies
    }

    private suspend fun getMoviesFromDB(): List<Movie> {
        lateinit var movieList: List<Movie>
        try {
            movieList = movieLocalDataSource.getMoviesFromDB() ?: emptyList()
            if (movieList.isNotEmpty()) {
                return movieList
            } else {
                movieList = getMoviesFromAPI()
                movieLocalDataSource.saveMoviesToDB(movieList)
            }
        } catch (e: Exception) {
            Log.e(
                "Repo Error",
                "Error getting movies from DB: ${e.message}, stacktrace: ${e.stackTrace}"
            )
        }
        return movieList
    }

    private suspend fun getMoviesFromAPI(): List<Movie> {
        lateinit var movieList: List<Movie>
        try {
            val response = movieRemoteDataSource.getMovies()
            val body = response.body()
            if (body != null) {
                movieList = body.movies
            }
        } catch (e: Exception) {
            Log.e(
                "Repo Error",
                "Error getting movies from API: ${e.message}, stacktrace: ${e.stackTrace}"
            )
        }
        return movieList
    }

    private suspend fun getMoviesFromCache(): List<Movie> {
       lateinit var movieList : List<Movie>
        try {
            movieList = movieCacheDataSource.getMoviesFromCache()
            if (movieList.isNotEmpty()) {
                return movieList
            } else {
                movieList = getMoviesFromDB()
                movieCacheDataSource.saveMoviesToCache(movieList)
            }
        } catch (e: Exception) {
            Log.e(
                "Repo Error",
                "Error getting movies from Cache: ${e.message}, stacktrace: ${e.stackTrace}"
            )
        }
        return movieList
    }
}