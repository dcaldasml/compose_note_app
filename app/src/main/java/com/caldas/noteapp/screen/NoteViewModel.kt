package com.caldas.noteapp.screen

import androidx.lifecycle.ViewModel
import com.caldas.noteapp.data.NotesDataSource
import com.caldas.noteapp.model.Note

class NoteViewModel: ViewModel() {
    private var noteList = mutableListOf<Note>()

    init {
        noteList.addAll(NotesDataSource().loadNotes())
    }

    fun addNote(note: Note) {
        noteList.add(note)
    }

    fun removeNote(note: Note) {
        noteList.remove(note)
    }

    fun getAllNotes(): List<Note> {
        return noteList
    }
}