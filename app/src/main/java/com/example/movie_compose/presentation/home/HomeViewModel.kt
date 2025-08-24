package com.example.movie_compose.presentation.home

import androidx.compose.runtime.Stable
import androidx.lifecycle.viewModelScope
import com.example.movie_compose.data.repository.HomeRepository
import com.example.movie_compose.presentation.base.viewmodel.BaseViewModel
import com.example.movie_compose.presentation.base.viewmodel.ViewModelStateFlow
import com.example.movie_compose.presentation.model.MovieUIData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
  private val homeRepository: HomeRepository,
) : BaseViewModel() {

  internal val uiState: ViewModelStateFlow<HomeUiState> = viewModelStateFlow(HomeUiState.Loading)

  private val movieFetchingIndex: MutableStateFlow<Int> = MutableStateFlow(1)

  val movieList: StateFlow<List<MovieUIData>> = movieFetchingIndex.flatMapLatest { page ->
      homeRepository.fetchMovieList(
        page = page,
        onStart = { uiState.tryEmit(key, HomeUiState.Loading) },
        onComplete = { uiState.tryEmit(key, HomeUiState.Idle) },
        onError = { uiState.tryEmit(key, HomeUiState.Error(it)) },
      )
    }
    .scan(emptyList<MovieUIData>()) { currentList, newMovies ->
      if (movieFetchingIndex.value == 1) {
        // 첫 페이지면 기존 리스트 초기화
        newMovies
      } else {
        // 다음 페이지면 기존 리스트에 추가
        currentList + newMovies
      }
    }
    .stateIn(
      scope = viewModelScope,
      started = SharingStarted.WhileSubscribed(5_000),
      initialValue = emptyList(),
    )

  fun fetchNextMovieList() {
    if (uiState.value != HomeUiState.Loading) {
      movieFetchingIndex.value++
    }
  }
}

@Stable
internal sealed interface HomeUiState {

  data object Idle : HomeUiState

  data object Loading : HomeUiState

  data class Error(val message: String?) : HomeUiState
}
