package com.caldas.noteapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.util.Date
import java.util.UUID

@Entity(tableName = "TB_NOTE")
data class Note(
    @PrimaryKey
    @ColumnInfo(name = "NOTE_ID")
    val id: UUID = UUID.randomUUID(),

    @ColumnInfo(name = "NOTE_TITLE")
    val title: String,

    @ColumnInfo(name = "NOTE_DESCRIPTION")
    val description: String,

    @ColumnInfo(name = "NOTE_ENTRY_DATE")
    val entryDate: Date = Date.from(Instant.now())
)
