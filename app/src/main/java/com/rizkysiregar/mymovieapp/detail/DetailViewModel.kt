package com.rizkysiregar.mymovieapp.detail

import androidx.lifecycle.ViewModel
import com.rizkysiregar.mymovieapp.core.domain.model.Movie
import com.rizkysiregar.mymovieapp.core.domain.usecase.MovieUseCase

class DetailViewModel(private val movieUseCase: MovieUseCase): ViewModel() {
    fun setFavoriteMovie(movie: Movie, state: Boolean) =
        movieUseCase.setFavoriteMovie(movie, state)
}