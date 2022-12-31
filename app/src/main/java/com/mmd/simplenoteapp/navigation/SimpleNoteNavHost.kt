package com.mmd.simplenoteapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.mmd.simplenoteapp.core.navigation.AppComposeNavigator
import com.mmd.simplenoteapp.core.navigation.SimpleNoteAppScreens

@Composable
fun SimpleNoteNavHost(navHostController: NavHostController, composeNavigator: AppComposeNavigator) {
  NavHost(
    navController = navHostController,
    startDestination = SimpleNoteAppScreens.Authentication.route
  ) {
    simpleNoteAppHomeNavigation(
      composeNavigator = composeNavigator
    )
  }
}
