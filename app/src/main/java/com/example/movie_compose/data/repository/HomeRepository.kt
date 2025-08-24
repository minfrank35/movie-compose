package com.example.movie_compose.data.repository

import androidx.annotation.WorkerThread
import com.example.movie_compose.presentation.model.MovieUIData
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
  @WorkerThread
  fun fetchMovieList(
    page: Int,
    onStart: () -> Unit,
    onComplete: () -> Unit,
    onError: (String?) -> Unit,
  ): Flow<List<MovieUIData>>
}
