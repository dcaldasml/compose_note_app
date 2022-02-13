package com.caldas.noteapp.data

import androidx.room.*
import com.caldas.noteapp.model.Note

@Dao
interface NoteDatabaseDao {

    @Query("SELECT * FROM TB_NOTE")
    fun getNotes(): List<Note>

    @Query("SELECT * FROM TB_NOTE WHERE NOTE_ID = :id")
    fun getNoteById(id: String): Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(note: Note)

    @Query("DELETE FROM TB_NOTE")
    fun deleteAll()

    @Delete
    fun deleteNote(note: Note)

}