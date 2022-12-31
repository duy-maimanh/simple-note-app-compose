package com.mmd.simplenoteapp.core.navigation

import androidx.navigation.NavOptions

sealed class NavigationCommand {
  object NavigationUp : NavigationCommand()
}

sealed class ComposeNavigationCommand : NavigationCommand() {
  data class NavigationRoute(val route: String, val options: NavOptions? = null) :
    ComposeNavigationCommand()

  data class NavigateUpWithResult<T>(val key: String, val result: T, val route: String? = null) :
    ComposeNavigationCommand()

  data class PopUpToRoute(val route: String, val inclusive: Boolean) : ComposeNavigationCommand()
}
