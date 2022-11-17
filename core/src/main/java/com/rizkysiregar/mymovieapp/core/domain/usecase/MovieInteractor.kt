package com.rizkysiregar.mymovieapp.core.domain.usecase

import com.rizkysiregar.mymovieapp.core.domain.model.Movie
import com.rizkysiregar.mymovieapp.core.domain.repository.IMovieRepository

class MovieInteractor(private val movieRepository: IMovieRepository): MovieUseCase {
    override fun getAllMovie() = movieRepository.getAllMovies()
    override fun getFavoriteMovie() = movieRepository.getFavoriteMovie()
    override fun setFavoriteMovie(movie: Movie, state: Boolean) = movieRepository.setFavoriteMovie(movie,state)

}