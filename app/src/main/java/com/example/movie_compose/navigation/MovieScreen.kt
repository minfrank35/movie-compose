package com.example.movie_compose.navigation

import com.example.movie_compose.presentation.model.MovieUIData
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

sealed interface MovieScreen {
  @Serializable
  data object Home : MovieScreen

  @Serializable
  data class Details(val movie: MovieUIData) : MovieScreen
}
