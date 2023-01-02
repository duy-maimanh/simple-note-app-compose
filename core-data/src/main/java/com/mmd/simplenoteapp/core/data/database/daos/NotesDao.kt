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

package com.mmd.simplenoteapp.core.data.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.mmd.simplenoteapp.core.data.database.entities.Note
import kotlinx.coroutines.flow.Flow

@Dao
abstract class NotesDao {

  @Query("SELECT * FROM notes")
  abstract fun getAllNotes(): Flow<List<Note>>

  @Insert
  abstract fun addNotes(notes: List<Note>)

  @Delete
  abstract fun deleteNote(note: Note)
}
