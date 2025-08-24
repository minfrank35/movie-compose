package com.example.movie_compose.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun MovieNavHost(navHostController: NavHostController) {
    NavHost(
      navController = navHostController,
      startDestination = MovieScreen.Home,
    ) {
      MovieNavigation()
    }
}

