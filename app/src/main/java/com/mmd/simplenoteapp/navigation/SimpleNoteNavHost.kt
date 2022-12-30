package com.mmd.simplenoteapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun SimpleNoteNavHost(navHostController: NavHostController) {
  NavHost(navController = navHostController, startDestination = "") {
    simpleNoteAppHomeNavigation()
  }
}
