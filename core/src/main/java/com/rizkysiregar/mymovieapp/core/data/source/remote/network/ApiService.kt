package com.rizkysiregar.mymovieapp.core.data.source.remote.network



import com.rizkysiregar.mymovieapp.core.BuildConfig
import com.rizkysiregar.mymovieapp.core.data.source.remote.response.DiscoverResponse
import retrofit2.http.GET


interface ApiService {
    @GET("discover/movie?api_key=${BuildConfig.MY_API_KEY}")
    suspend fun getDiscover(): DiscoverResponse
}