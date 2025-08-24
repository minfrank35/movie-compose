package com.example.movie_compose.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.movie_compose.presentation.detail.DetailView
import com.example.movie_compose.presentation.home.HomeView
import com.example.movie_compose.presentation.model.MovieUIData
import kotlin.reflect.typeOf

fun NavGraphBuilder.MovieNavigation() {
  composable<MovieScreen.Home> {
    HomeView()
  }

  composable<MovieScreen.Details>(
    typeMap = mapOf(typeOf<MovieUIData>() to CustomNavType.movieUiType)
  ){
    DetailView()
  }
}
