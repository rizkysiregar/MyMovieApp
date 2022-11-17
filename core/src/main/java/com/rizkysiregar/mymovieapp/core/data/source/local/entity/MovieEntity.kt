package com.rizkysiregar.mymovieapp.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    val id: Int = 0,

    @ColumnInfo(name= "overview")
    val overview: String,

    @ColumnInfo(name= "original_language")
    val originalLanguage: String,

    @ColumnInfo(name="original_title")
    val originalTitle: String,

    @ColumnInfo(name="title")
    val title: String,

    @ColumnInfo(name="poster_path")
    val posterPath: String,

    @ColumnInfo(name ="backdrop_path")
    val backdropPath: String,

    @ColumnInfo(name="release_date")
    val releaseDate: String,

    @ColumnInfo(name = "popularity")
    val popularity: Double,

    @ColumnInfo(name="vote_average")
    val voteAverage: Double,

    @ColumnInfo(name="adult")
    val adult: Boolean,

    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false
)
