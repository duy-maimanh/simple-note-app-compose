package com.mmd.simplenoteapp.core.model

import java.util.Date

data class SimpleNote(
    val id: Long,
    val message: String,
    val dateTime: Date,
    // 1 is waiting, 2 is done and three is forgot
    val status: Int
)
