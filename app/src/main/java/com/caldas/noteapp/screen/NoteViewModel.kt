package com.caldas.noteapp.screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.caldas.noteapp.model.Note
import com.caldas.noteapp.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel() {

    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()
    val title = mutableStateOf("")
    val description = mutableStateOf("")
    var isUpdating = mutableStateOf(false)
    private lateinit var updatingNote: Note

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllNotes().distinctUntilChanged().collect { listOfNotes ->
                _noteList.value = listOfNotes
            }
        }
    }

    fun addNote(note: Note) = viewModelScope.launch {
        if (isUpdating.value) {
            val c = updatingNote.copy(title = note.title, description = note.description)
            repository.updateNote(c)
            isUpdating.value = false
        } else {
            repository.addNote(note)
        }
    }

    fun updateNote(note: Note) {
        title.value = note.title
        description.value = note.description
        isUpdating.value = true
        updatingNote = note
    }

    fun removeNote(note: Note) = viewModelScope.launch { repository.deleteNote(note) }

}