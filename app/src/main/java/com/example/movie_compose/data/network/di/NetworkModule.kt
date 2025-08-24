package com.example.movie_compose.data.network.di

import com.example.movie_compose.BuildConfig
import com.example.movie_compose.data.network.service.MovieClient
import com.example.movie_compose.data.network.service.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer ${BuildConfig.ACCESS_TOKEN}")
                    .build()
                chain.proceed(request)
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

     @Provides
     @Singleton
     fun provideMovieService(retrofit: Retrofit): MovieService {
         return retrofit.create(MovieService::class.java)
     }

     @Provides
     @Singleton
     fun provideMovieClient(movieService: MovieService): MovieClient {
         return MovieClient(movieService)
     }
}