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

package com.mmd.simplenoteapp.core.data.repository

import android.util.Log
import com.mmd.simplenoteapp.core.data.database.daos.NotesDao
import com.mmd.simplenoteapp.core.data.database.entities.Note
import com.mmd.simplenoteapp.core.model.SimpleNote
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class NoteRepositoryImpl @Inject constructor(private val notesDao: NotesDao) :
  NoteRepository {
  override suspend fun addNotes(vararg note: SimpleNote) {
    try {
      note.map {
        Note.fromDomain(it)!!
      }.let { notes ->
        notesDao.addNotes(notes)
      }
    } catch (e: NullPointerException) {
      Log.e("NoteRepositoryImpl", e.message.toString())
    }
  }

  override fun getAllNote(): Flow<List<SimpleNote>> {
    TODO("Not yet implemented")
  }

  override suspend fun deleteNote() {
    TODO("Not yet implemented")
  }
}
