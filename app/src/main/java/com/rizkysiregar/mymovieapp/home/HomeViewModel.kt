package com.rizkysiregar.mymovieapp.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rizkysiregar.mymovieapp.core.domain.usecase.MovieUseCase

class HomeViewModel(movieUseCase: MovieUseCase): ViewModel() {
    val movie = movieUseCase.getAllMovie().asLiveData()
}