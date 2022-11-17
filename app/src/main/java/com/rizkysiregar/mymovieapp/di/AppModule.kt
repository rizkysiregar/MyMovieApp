package com.rizkysiregar.mymovieapp.di

import com.rizkysiregar.mymovieapp.core.domain.usecase.MovieInteractor
import com.rizkysiregar.mymovieapp.core.domain.usecase.MovieUseCase
import com.rizkysiregar.mymovieapp.detail.DetailViewModel
import com.rizkysiregar.mymovieapp.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase>{ MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get())}
}