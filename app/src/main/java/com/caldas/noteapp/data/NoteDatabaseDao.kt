package com.caldas.noteapp.data

import androidx.room.*
import com.caldas.noteapp.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDatabaseDao {

    @Query("SELECT * FROM TB_NOTE")
    fun getNotes(): Flow<List<Note>>

    @Query("SELECT * FROM TB_NOTE WHERE NOTE_ID = :id")
    suspend fun getNoteById(id: String): Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(note: Note)

    @Query("DELETE FROM TB_NOTE")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteNote(note: Note)

}