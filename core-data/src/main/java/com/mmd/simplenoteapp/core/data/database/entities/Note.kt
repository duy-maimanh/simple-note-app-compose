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

package com.mmd.simplenoteapp.core.data.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.mmd.simplenoteapp.core.model.NormalNote
import com.mmd.simplenoteapp.core.model.SimpleNote
import com.mmd.simplenoteapp.core.model.TodoNote
import java.util.Date

@Entity(
  tableName = "notes",
  foreignKeys = [
    ForeignKey(
      entity = Background::class,
      parentColumns = ["backgroundId"],
      childColumns = ["backgroundId"],
      onDelete = ForeignKey.CASCADE
    )
  ]
)
data class Note(
  @PrimaryKey(autoGenerate = true)
  val noteId: Long = 0,
  val type: Int,
  val message: String,
  val createTime: Date,
  val notifyTime: Date?,
  val isDone: Boolean,
  val title: String,
  val backgroundId: Long
) {
  companion object {
    const val TYPE_NORMAL = 0
    const val TYPE_TODO = 1

    fun fromDomain(note: SimpleNote): Note? {
      return when (note) {
        is NormalNote -> {
          Note(
            title = note.title,
            type = TYPE_NORMAL,
            message = note.message,
            createTime = note.createTime,
            notifyTime = note.notifyTime,
            isDone = false,
            backgroundId = note.backgroundId
          )
        }
        is TodoNote -> {
          Note(
            title = "",
            type = TYPE_TODO,
            message = note.message,
            createTime = note.createTime,
            notifyTime = note.notifyTime,
            isDone = false,
            backgroundId = 0
          )
        }
        else -> {
          null
        }
      }
    }
  }

  fun toDomain(): SimpleNote? {
    return when (type) {
      TYPE_NORMAL -> {
        NormalNote(noteId, message, createTime, notifyTime, title, backgroundId)
      }
      TYPE_TODO -> {
        TodoNote(noteId, message, createTime, notifyTime, isDone)
      }
      else -> {
        null
      }
    }
  }
}
