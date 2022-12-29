package com.example.themoviebookingapp.persistance.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.themoviebookingapp.data.vos.MovieVO

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieList(movieList : List<MovieVO>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSingleMovie(movie : MovieVO?)

    @Query("SELECT * FROM movies WHERE type = :type")
    fun getMovieListByType(type: String) : List<MovieVO>

    @Query("SELECT * FROM movies WHERE id = :movieId")
    fun getMovieById(movieId: Int) : MovieVO?
}