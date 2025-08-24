package com.example.movie_compose.navigation

import androidx.navigation.NavOptionsBuilder
import androidx.navigation.navOptions
import javax.inject.Inject

class MovieComposeNavigator @Inject constructor() : AppComposeNavigator<MovieScreen>() {

  override fun navigate(route: MovieScreen, optionsBuilder: (NavOptionsBuilder.() -> Unit)?) {
    val options = optionsBuilder?.let { navOptions(it) }
    navigationCommands.tryEmit(ComposeNavigationCommand.NavigateToRoute(route, options))
  }

  override fun navigateAndClearBackStack(route: MovieScreen) {
    navigationCommands.tryEmit(
      ComposeNavigationCommand.NavigateToRoute(
        route,
        navOptions {
          popUpTo(0)
        },
      ),
    )
  }

  override fun popUpTo(route: MovieScreen, inclusive: Boolean) {
    navigationCommands.tryEmit(ComposeNavigationCommand.PopUpToRoute(route, inclusive))
  }

  override fun <R> navigateBackWithResult(key: String, result: R, route: MovieScreen?) {
    navigationCommands.tryEmit(
      ComposeNavigationCommand.NavigateUpWithResult(
        key = key,
        result = result,
        route = route,
      ),
    )
  }
}
