package com.example.movieapp.presentation.di

import com.example.movieapp.data.api.TMDBService
import com.example.movieapp.data.datasource.MovieRemoteDataSource
import com.example.movieapp.data.datasourceImpl.MovieRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteDataModule(private val apiKey: String) {

    @Singleton
    @Provides
    fun providesRemoteDataSource(tmdbService: TMDBService) : MovieRemoteDataSource {
        return MovieRemoteDataSourceImpl(tmdbService, apiKey)
    }
}