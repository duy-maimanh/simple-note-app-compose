package com.mmd.simplenoteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import com.mmd.simplenoteapp.core.designsystem.theme.SimpleNoteAppTheme
import com.mmd.simplenoteapp.core.navigation.AppComposeNavigator
import com.mmd.simplenoteapp.ui.NoteAppMain
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  @Inject
  internal lateinit var appComposeNavigator: AppComposeNavigator

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      CompositionLocalProvider {
        SimpleNoteAppTheme {
          NoteAppMain(composeNavigator = appComposeNavigator)
        }
      }
    }
  }
}
