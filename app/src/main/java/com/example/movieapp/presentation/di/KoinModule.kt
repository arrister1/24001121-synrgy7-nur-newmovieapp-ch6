package com.example.movieapp.presentation.di

import com.example.movieapp.datas.local.DataStore
import com.example.movieapp.datas.remote.RemoteDataSource
import com.example.movieapp.datas.remote.network.ApiService
import com.example.movieapp.datas.remote.repository.MovieRepository
import com.example.movieapp.domain.repository.IMovieRepository
import com.example.movieapp.domain.usecase.MovieInteractor
import com.example.movieapp.domain.usecase.MovieUseCase
import com.example.movieapp.presentation.viewmodel.MovieViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object KoinModule {

    val networkModule = module {
        single {
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
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
        single { RemoteDataSource(get()) }
        single<IMovieRepository> { MovieRepository(get()) }
    }

    val dataModule = module {
        single { DataStore(androidContext()) }}
    val useCaseModule = module {
        factory<MovieUseCase> { MovieInteractor(get()) }
    }




val viewModelModule = module {
        viewModel { MovieViewModel(get()) }

    }
}