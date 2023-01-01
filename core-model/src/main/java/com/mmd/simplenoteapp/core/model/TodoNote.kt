package com.mmd.simplenoteapp.core.model

import java.util.Date

data class TodoNote(
    override val id: Long,
    override val message: String,
    override val dateTime: Date,
    val isDone: Boolean = false
) : SimpleNote(id, message, dateTime)
