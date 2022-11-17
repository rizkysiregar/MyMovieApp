package com.rizkysiregar.mymovieapp.core.domain.usecase

import com.rizkysiregar.mymovieapp.core.data.Resource
import com.rizkysiregar.mymovieapp.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getAllMovie(): Flow<Resource<List<Movie>>>
    fun getFavoriteMovie(): Flow<List<Movie>>
    fun setFavoriteMovie(movie: Movie, state: Boolean)
}