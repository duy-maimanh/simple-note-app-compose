package com.mmd.simplenoteapp.core.navigation

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NavigationModule {

  @Binds
  @Singleton
  abstract fun provideComposeNavigation(
    noteAppComposeNavigator: SimpleNoteAppComposeNavigator
  ): AppComposeNavigator
}
