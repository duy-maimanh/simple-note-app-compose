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

package com.mmd.simplenoteapp.core.data.di

import android.content.Context
import androidx.room.Room
import com.mmd.simplenoteapp.core.data.database.SimpleNoteAppDatabase
import com.mmd.simplenoteapp.core.data.database.daos.NotesDao
import com.mmd.simplenoteapp.core.data.repository.NoteRepository
import com.mmd.simplenoteapp.core.data.repository.NoteRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
internal abstract class DataModule() {

    @get:Binds
    abstract val NoteRepositoryImpl.noteRepository: NoteRepository

    companion object {
        private const val DATABASE_NAME = "note-app.db"

        @Provides
        @Singleton
        fun provideDatabase(@ApplicationContext applicationContext: Context): SimpleNoteAppDatabase {
            return Room.databaseBuilder(
                applicationContext,
                SimpleNoteAppDatabase::class.java,
                DATABASE_NAME
            ).build()
        }

        @Provides
        fun provideNotesDao(simpleNoteAppDatabase: SimpleNoteAppDatabase): NotesDao =
            simpleNoteAppDatabase.notesDao()
    }
}
