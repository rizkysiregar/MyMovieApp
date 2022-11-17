package com.rizkysiregar.mymovieapp

import android.app.Application
import com.rizkysiregar.mymovieapp.core.di.databaseModule
import com.rizkysiregar.mymovieapp.core.di.networkModule
import com.rizkysiregar.mymovieapp.core.di.repositoryModule
import com.rizkysiregar.mymovieapp.di.useCaseModule
import com.rizkysiregar.mymovieapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}