package com.rizkysiregar.mymovieapp.core.di


import androidx.room.Room
import com.rizkysiregar.mymovieapp.core.BuildConfig
import com.rizkysiregar.mymovieapp.core.data.MovieRepository
import com.rizkysiregar.mymovieapp.core.data.source.local.LocalDataSource
import com.rizkysiregar.mymovieapp.core.data.source.local.room.MovieDatabase
import com.rizkysiregar.mymovieapp.core.data.source.remote.RemoteDataSource
import com.rizkysiregar.mymovieapp.core.data.source.remote.network.ApiService
import com.rizkysiregar.mymovieapp.core.domain.repository.IMovieRepository
import com.rizkysiregar.mymovieapp.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    // encrypt database with sqlchipher
    factory { get<MovieDatabase>().movieDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("rizkysiregar".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            MovieDatabase::class.java, "Movie.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        // ADD CERTIFICATE PINNING
        val loggingInterceptor = if(BuildConfig.DEBUG) { HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY) }else { HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE) }
        val hostname = "api.themoviedb.org"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/p+WeEuGncQbjSKYPSzAaKpF/iLcOjFLuZubtsXupYSI=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IMovieRepository>{ MovieRepository( get(),get(),get() ) }
}