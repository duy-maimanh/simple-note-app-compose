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
