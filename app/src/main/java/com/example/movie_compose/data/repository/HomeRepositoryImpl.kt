package com.example.movie_compose.data.repository

import androidx.annotation.WorkerThread
import com.example.movie_compose.BuildConfig
import com.example.movie_compose.data.network.service.MovieClient
import com.example.movie_compose.presentation.model.MovieUIData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val movieClient: MovieClient
) : HomeRepository {

    @WorkerThread
    override fun fetchMovieList(
        page: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit,
    ) = flow {
        val response = movieClient.getPopularMovies(
            accessToken = BuildConfig.ACCESS_TOKEN,
            page = page
        )
        
        if (response.isSuccessful) {
            val movies = response.body()?.results?.map { movie ->
                MovieUIData(
                    name = movie.title,
                    url = movie.getPosterUrl() ?: ""
                )
            } ?: emptyList()
            
            emit(movies)
        } else {
            onError("HTTP ${response.code()}: ${response.message()}")
        }
    }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(Dispatchers.IO)
}