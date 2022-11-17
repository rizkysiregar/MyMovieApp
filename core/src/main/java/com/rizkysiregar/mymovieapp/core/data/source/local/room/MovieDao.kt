package com.rizkysiregar.mymovieapp.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.rizkysiregar.mymovieapp.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    // ambil semua data dari table database
    @Query("SELECT * FROM movie")
    fun getAllMovies(): Flow<List<MovieEntity>>

    // insert data ke dalam database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: List<MovieEntity>)

    // favorite movie dari database
    @Query("SELECT * FROM movie WHERE favorite = 1")
    fun getFavoriteMovie(): Flow<List<MovieEntity>>

    // update status favorite atau tidak
    @Update
    fun updateFavoriteMovie(movie: MovieEntity)
}