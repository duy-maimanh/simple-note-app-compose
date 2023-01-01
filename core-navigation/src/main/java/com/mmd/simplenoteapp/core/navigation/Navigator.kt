/*
 * Developed by 2022 Duy Mai.
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
package com.mmd.simplenoteapp.core.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onSubscription

abstract class Navigator {
  val navigationCommand =
    MutableSharedFlow<NavigationCommand>(extraBufferCapacity = Int.MAX_VALUE)

  val navControllerFlow = MutableStateFlow<NavController?>(null)

  fun navigateUp() {
    navigationCommand.tryEmit(NavigationCommand.NavigationUp)
  }
}

abstract class AppComposeNavigator : Navigator() {
  abstract fun navigate(route: String, optionsBuilder: (NavOptionsBuilder.() -> Unit)? = null)
  abstract fun <T> navigateBackWithResult(key: String, result: T, route: String?)
  abstract fun popUpTo(route: String, inclusive: Boolean)
  abstract fun navigateAndClearBackStack(route: String)

  suspend fun handleNavigationCommands(navController: NavController) {
    navigationCommand.onSubscription {
      this@AppComposeNavigator.navControllerFlow.value = navController
    }.onCompletion {
      this@AppComposeNavigator.navControllerFlow.value = null
    }.collect {
      navController.handleComposeNavigationCommand(it)
    }
  }

  private fun NavController.handleComposeNavigationCommand(navigationCommand: NavigationCommand) {
    when (navigationCommand) {
      is NavigationCommand.NavigationUp -> {
        navigateUp()
      }
      is ComposeNavigationCommand.NavigationRoute -> {
        navigate(navigationCommand.route, navigationCommand.options)
      }
      is ComposeNavigationCommand.PopUpToRoute -> {
        popBackStack(navigationCommand.route, navigationCommand.inclusive)
      }
      is ComposeNavigationCommand.NavigateUpWithResult<*> -> {
        navUpWithResult(navigationCommand)
      }
    }
  }

  private fun NavController.navUpWithResult(
    navigationCommand: ComposeNavigationCommand.NavigateUpWithResult<*>
  ) {
    val backStackEntry =
      navigationCommand.route?.let { getBackStackEntry(it) }
        ?: previousBackStackEntry
    backStackEntry?.savedStateHandle?.set(
      navigationCommand.key,
      navigationCommand.result
    )

    navigationCommand.route?.let {
      popBackStack(it, false)
    } ?: run {
      navigateUp()
    }
  }
}
