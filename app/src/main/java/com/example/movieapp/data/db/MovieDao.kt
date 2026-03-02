package com.example.movieapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieapp.data.model.Movie

///This class is a placeholder for the actual DAO (Data Access Object) that will be used to interact with the database.
///It will contain methods for inserting, updating, deleting, and querying movies from the database.
///The actual implementation will depend on the specific database library being used (e.g., Room, SQLite, etc.)
@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovies(movies: List<Movie>)

    @Query("SELECT * FROM popular_movies")
    suspend fun getMovies(): List<Movie>

    @Query("DELETE FROM popular_movies")
    suspend fun deleteAllMovies()

}