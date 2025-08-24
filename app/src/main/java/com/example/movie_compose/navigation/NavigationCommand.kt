package com.example.movie_compose.navigation

import androidx.navigation.NavOptions

sealed interface NavigationCommand {
  data object NavigateUp : NavigationCommand
}

sealed interface ComposeNavigationCommand : NavigationCommand {
  /**
   * 지정된 경로로 이동하는 명령
   * @param route 이동할 대상 경로
   * @param options 네비게이션 옵션 (선택사항)
   */
  data class NavigateToRoute<T : Any>(val route: T, val options: NavOptions? = null) :
    ComposeNavigationCommand

  /**
   * 결과를 전달하면서 상위 화면으로 이동하는 명령
   * @param key 결과를 식별하는 키
   * @param result 전달할 결과 데이터
   * @param route 이동할 대상 경로 (선택사항)
   */
  data class NavigateUpWithResult<R, T : Any>(
    val key: String,
    val result: R,
    val route: T? = null,
  ) : ComposeNavigationCommand

  /**
   * 지정된 경로까지 백스택을 팝하는 명령
   * @param route 팝할 대상 경로
   * @param inclusive 대상 경로도 포함하여 팝할지 여부
   */
  data class PopUpToRoute<T : Any>(val route: T, val inclusive: Boolean) :
    ComposeNavigationCommand
}
