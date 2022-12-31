package com.mmd.simplenoteapp.core.navigation

import androidx.navigation.NamedNavArgument

sealed class SimpleNoteAppScreens(
  val route: String,
  val index: Int? = null,
  val navArgument: List<NamedNavArgument> = emptyList()
) {
  val name: String = route.appendArguments(navArgument)

  // authentication screen
  object Authentication : SimpleNoteAppScreens("authentication")

  // notes screen
  object Notes : SimpleNoteAppScreens("notes")

  // setting screen
  object Settings : SimpleNoteAppScreens("setting")
}

private fun String.appendArguments(navArguments: List<NamedNavArgument>): String {
  val mandatoryArguments = navArguments.filter { it.argument.defaultValue == null }
    .takeIf { it.isNotEmpty() }
    ?.joinToString(separator = "/", prefix = "/") { "{${it.name}}" }
    .orEmpty()
  val optionalArguments = navArguments.filter { it.argument.defaultValue != null }
    .takeIf { it.isNotEmpty() }
    ?.joinToString(separator = "&", prefix = "?") { "${it.name}={${it.name}}" }
    .orEmpty()
  return "$this$mandatoryArguments$optionalArguments"
}
