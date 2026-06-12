package com.example.movieapp.presentation.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movieapp.data.db.MovieDao
import com.example.movieapp.data.db.TMDBDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideMovieDatabase(context: Context): RoomDatabase {
        return Room.databaseBuilder(
            context,
            TMDBDatabase::class.java,
            "tmdbClient"
        ).build()
    }

    @Singleton
    @Provides
    fun provideMovieDAO(tmdbDatabase: TMDBDatabase): MovieDao {
        return tmdbDatabase.movieDao()
    }
}