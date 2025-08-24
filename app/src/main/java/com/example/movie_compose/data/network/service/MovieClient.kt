package com.example.movie_compose.data.network.service

import com.example.movie_compose.data.network.model.res.PopularMoviesResponse
import retrofit2.Response
import javax.inject.Inject

class MovieClient @Inject constructor(
   private val movieService: MovieService
) {
   
   suspend fun getPopularMovies(
       accessToken: String,
       language: String = "ko-KR",
       page: Int = 1
   ): Response<PopularMoviesResponse> {
       return movieService.getPopularMovies(
           authorization = "Bearer $accessToken",
           language = language,
           page = page
       )
   }
}