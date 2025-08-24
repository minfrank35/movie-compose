package com.example.movie_compose.data.network.service

import com.example.movie_compose.data.network.model.res.PopularMoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MovieService {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Header("Authorization") authorization: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
    ): Response<PopularMoviesResponse>
}