package com.example.movie_compose.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.example.movie_compose.presentation.theme.MoviecomposeTheme
import com.bumptech.glide.integration.compose.GlideImage
import com.example.movie_compose.presentation.model.MovieUIData
import com.example.movie_compose.navigation.LocalComposeNavigator
import com.example.movie_compose.navigation.MovieScreen
import com.example.movie_compose.navigation.currentComposeNavigator

@Composable
fun HomeView(
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    val movieList by homeViewModel.movieList.collectAsState()
    val uiState by homeViewModel.uiState.collectAsState()
    
    Scaffold(
        topBar = {
            Text(
                text = "Movies",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(16.dp)
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            val threadHold = 18
            when (val currentState = uiState) {
                is HomeUiState.Error -> {
                    Text(
                        text = "Error: ${currentState.message ?: "Unknown error"}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.error,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                is HomeUiState.Idle, HomeUiState.Loading -> {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        itemsIndexed(
                            items = movieList,
                            key = { _, movie -> movie.url }
                        ) { index, movie ->
                            if ((index + threadHold) >= movieList.size && uiState != HomeUiState.Loading) {
                                homeViewModel.fetchNextMovieList()
                            }

                            MovieCard(movie = movie)
                        }
                    }

                    if(uiState == HomeUiState.Loading) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun MovieCard(
    movie: MovieUIData,
    modifier: Modifier = Modifier
) {
    val composeNavigator = currentComposeNavigator
    Card(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(0.7f)
            .clickable {
                composeNavigator.navigate(MovieScreen.Details(movie = movie))
            },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            GlideImage(
                model = movie.url,
                contentDescription = movie.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)),
                contentScale = ContentScale.Crop
            )
            Text(
                text = movie.name,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieCardPreview() {
    val sampleMovie = MovieUIData(
        name = "Avengers: Endgame", 
        url = "https://image.tmdb.org/t/p/w500/aSrMJYmQX8kpF26LijkCsYhBMvm.jpg"
    )
    
    MoviecomposeTheme {
        MovieCard(movie = sampleMovie)
    }
}