package com.rizkysiregar.mymovieapp.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val overview: String,
    val originalLanguage: String,
    val originalTitle: String,
    val title: String,
    val posterPath: String,
    val backdropPath: String,
    val releaseDate: String,
    val popularity: Double,
    val voteAverage: Double,
    val adult: Boolean,
    var favorite: Boolean = false
): Parcelable
