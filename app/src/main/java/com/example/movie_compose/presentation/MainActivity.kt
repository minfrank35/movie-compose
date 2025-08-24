package com.example.movie_compose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import com.example.movie_compose.navigation.AppComposeNavigator
import com.example.movie_compose.navigation.LocalComposeNavigator
import com.example.movie_compose.navigation.MovieScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    internal lateinit var composeNavigator: AppComposeNavigator<MovieScreen>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CompositionLocalProvider(
                LocalComposeNavigator provides composeNavigator,
            ) {
                MovieMain(composeNavigator = composeNavigator)
            }
        }
    }
}
