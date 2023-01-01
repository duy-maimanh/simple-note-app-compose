package com.mmd.simplenoteapp.core.navigation

import androidx.navigation.NavOptionsBuilder
import androidx.navigation.navOptions
import javax.inject.Inject

class SimpleNoteAppComposeNavigator @Inject constructor() : AppComposeNavigator() {
  override fun navigate(route: String, optionsBuilder: (NavOptionsBuilder.() -> Unit)?) {
    val options = optionsBuilder?.let { navOptions(it) }
    navigationCommand.tryEmit(ComposeNavigationCommand.NavigationRoute(route, options))
  }

  override fun <T> navigateBackWithResult(key: String, result: T, route: String?) {
    navigationCommand.tryEmit(ComposeNavigationCommand.NavigateUpWithResult(key, result, route))
  }

  override fun popUpTo(route: String, inclusive: Boolean) {
    navigationCommand.tryEmit(ComposeNavigationCommand.PopUpToRoute(route, inclusive))
  }

  override fun navigateAndClearBackStack(route: String) {
    navigationCommand.tryEmit(
      ComposeNavigationCommand.NavigationRoute(
        route,
        navOptions {
          popUpTo(0)
        }
      )
    )
  }
}
