package com.mmd.simplenoteapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.rememberNavController
import com.mmd.simplenoteapp.core.designsystem.theme.SimpleNoteAppTheme
import com.mmd.simplenoteapp.core.navigation.AppComposeNavigator
import com.mmd.simplenoteapp.navigation.SimpleNoteNavHost

@Composable
fun NoteAppMain(composeNavigator: AppComposeNavigator) {
  SimpleNoteAppTheme {
    val navHostController = rememberNavController()

    LaunchedEffect(Unit) {
      composeNavigator.handleNavigationCommands(navHostController)
    }

    SimpleNoteNavHost(
      navHostController = navHostController,
      composeNavigator = composeNavigator
    )
  }
}
