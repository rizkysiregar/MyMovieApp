package com.rizkysiregar.mymovieapp.core.utils

import com.rizkysiregar.mymovieapp.core.data.source.local.entity.MovieEntity
import com.rizkysiregar.mymovieapp.core.data.source.remote.response.ResultsItem
import com.rizkysiregar.mymovieapp.core.domain.model.Movie

object DataMapper {
    fun mapEntityToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                id = it.id,
                overview = it.overview,
                originalLanguage = it.originalLanguage,
                originalTitle = it.originalTitle,
                title = it.title,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                releaseDate = it.releaseDate,
                popularity = it.popularity,
                voteAverage = it.voteAverage,
                adult = it.adult,
                favorite = it.favorite
            )
        }

    fun mapDomainToEntity(input: Movie) = MovieEntity(
        id = input.id,
        overview = input.overview,
        originalLanguage = input.originalLanguage,
        originalTitle = input.originalTitle,
        title = input.title,
        posterPath = input.posterPath,
        backdropPath = input.backdropPath,
        releaseDate = input.releaseDate,
        popularity = input.popularity,
        voteAverage = input.voteAverage,
        adult = input.adult,
        favorite = input.favorite
    )

    fun mapResponseToEntities(input: List<ResultsItem>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                id = it.id,
                overview = it.overview,
                originalLanguage = it.originalLanguage,
                originalTitle = it.originalTitle,
                title = it.title,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                releaseDate = it.releaseDate,
                popularity = it.popularity,
                voteAverage = it.voteAverage,
                adult = it.adult,
                favorite = false
            )
            movieList.add(movie)
        }
        return movieList
    }
}