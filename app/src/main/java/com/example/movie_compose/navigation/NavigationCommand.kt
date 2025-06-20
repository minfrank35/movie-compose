/*
 * Designed and developed by 2024 skydoves (Jaewoong Eum)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
