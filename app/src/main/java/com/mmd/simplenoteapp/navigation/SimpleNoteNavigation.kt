package com.mmd.simplenoteapp.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mmd.simplenoteapp.core.navigation.AppComposeNavigator
import com.mmd.simplenoteapp.core.navigation.SimpleNoteAppScreens

fun NavGraphBuilder.simpleNoteAppHomeNavigation(
  composeNavigator: AppComposeNavigator
) {
  composable(route = SimpleNoteAppScreens.Authentication.name) {
  }

  composable(route = SimpleNoteAppScreens.Notes.name) {
  }

  composable(route = SimpleNoteAppScreens.Settings.name) {
  }
}
