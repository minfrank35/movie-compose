package com.example.movie_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.movie_compose.navigation.AppComposeNavigator
import com.example.movie_compose.navigation.LocalComposeNavigator
import com.example.movie_compose.navigation.MovieScreen
import com.example.movie_compose.ui.theme.MoviecomposeTheme

class MainActivity : ComponentActivity() {

    internal lateinit var composeNavigator: AppComposeNavigator<MovieScreen>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CompositionLocalProvider(
                LocalComposeNavigator provides composeNavigator,
            ) {

            }
        }
    }
}
