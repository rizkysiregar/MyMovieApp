package com.rizkysiregar.mymovieapp.core.domain.repository

import com.rizkysiregar.mymovieapp.core.data.Resource
import com.rizkysiregar.mymovieapp.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getAllMovies(): Flow<Resource<List<Movie>>>
    fun getFavoriteMovie(): Flow<List<Movie>>
    fun setFavoriteMovie(movie: Movie, state: Boolean)
}