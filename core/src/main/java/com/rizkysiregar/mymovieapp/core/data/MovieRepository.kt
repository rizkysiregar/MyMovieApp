package com.rizkysiregar.mymovieapp.core.data

import com.rizkysiregar.mymovieapp.core.data.source.local.LocalDataSource
import com.rizkysiregar.mymovieapp.core.data.source.remote.RemoteDataSource
import com.rizkysiregar.mymovieapp.core.data.source.remote.network.ApiResponse
import com.rizkysiregar.mymovieapp.core.data.source.remote.response.ResultsItem
import com.rizkysiregar.mymovieapp.core.domain.model.Movie
import com.rizkysiregar.mymovieapp.core.domain.repository.IMovieRepository
import com.rizkysiregar.mymovieapp.core.utils.AppExecutors
import com.rizkysiregar.mymovieapp.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository  constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): IMovieRepository {

    override fun getAllMovies(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<ResultsItem>>() {
            override suspend fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovie().map { DataMapper.mapEntityToDomain(it) }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean  =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ResultsItem>>> =
                remoteDataSource.getAllMovie()

            override suspend fun saveCallResult(data: List<ResultsItem>) {
                val movieList = DataMapper.mapResponseToEntities(data)
                localDataSource.insertMovie(movieList)
            }
        }.asFlow()

    override fun getFavoriteMovie(): Flow<List<Movie>> {
       return localDataSource.getFavoriteMovie().map {
           DataMapper.mapEntityToDomain(it)
       }
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movieEntity,state) }
    }
}