package com.example.movie_compose.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.rememberNavController
import com.example.movie_compose.navigation.AppComposeNavigator
import com.example.movie_compose.navigation.MovieScreen
import com.example.movie_compose.navigation.MovieNavHost
import com.example.movie_compose.presentation.theme.MoviecomposeTheme

@Composable
fun MovieMain(composeNavigator: AppComposeNavigator<MovieScreen>) {
    MoviecomposeTheme {
        val navHostController = rememberNavController()

        LaunchedEffect(Unit) {
            composeNavigator.handleNavigationCommands(navHostController)
        }

        MovieNavHost(navHostController = navHostController)
    }
}