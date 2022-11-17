package com.rizkysiregar.mymovieapp.core.data.source.remote

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.rizkysiregar.mymovieapp.core.data.source.remote.network.ApiResponse
import com.rizkysiregar.mymovieapp.core.data.source.remote.network.ApiService
import com.rizkysiregar.mymovieapp.core.data.source.remote.response.ResultsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource  constructor(private val apiService: ApiService) {

    suspend fun getAllMovie(): Flow<ApiResponse<List<ResultsItem>>> {
        val resultData = MutableLiveData<ApiResponse<List<ResultsItem>>>()

        // ambil data dari api
        return flow {
            try {
                val response = apiService.getDiscover()
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}