package com.rizkysiregar.mymovieapp.core.data.source.local

import com.rizkysiregar.mymovieapp.core.data.source.local.entity.MovieEntity
import com.rizkysiregar.mymovieapp.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource  constructor(private val movieDao: MovieDao) {
    fun getAllMovie(): Flow<List<MovieEntity>> = movieDao.getAllMovies()
    suspend fun insertMovie(movieList: List<MovieEntity>) = movieDao.insertMovie(movieList)
    fun getFavoriteMovie(): Flow<List<MovieEntity>> = movieDao.getFavoriteMovie()

    fun setFavoriteMovie(movieEntity: MovieEntity, state: Boolean) {
        movieEntity.favorite = state
        movieDao.updateFavoriteMovie(movieEntity)
    }
}