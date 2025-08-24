

package com.example.movie_compose.navigation.di

import com.example.movie_compose.navigation.AppComposeNavigator
import com.example.movie_compose.navigation.MovieComposeNavigator
import com.example.movie_compose.navigation.MovieScreen
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface NavigationModule {

  @Binds
  @Singleton
  fun provideComposeNavigator(
    movieComposeNavigator: MovieComposeNavigator,
  ): AppComposeNavigator<MovieScreen>
}
