package com.caldas.noteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import com.caldas.noteapp.screen.NoteScreen
import com.caldas.noteapp.screen.NoteViewModel
import com.caldas.noteapp.ui.theme.NoteAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalFoundationApi
    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val noteViewModel: NoteViewModel by viewModels()
                    NotesApp(noteViewModel)
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@Composable
fun NotesApp(noteViewModel: NoteViewModel) {
    val notesList = noteViewModel.noteList.collectAsState().value
    val title = noteViewModel.title
    val description = noteViewModel.description
    NoteScreen(
        title = title,
        description = description,
        notes = notesList,
        isUpdating = noteViewModel.isUpdating.value,
        onAddNote = { noteViewModel.addNote(it) },
        onRemoveNote = { noteViewModel.removeNote(it) },
        onUpdateNote = { noteViewModel.updateNote(it) },
        onRemoveAllNotes = { noteViewModel.removeAllNotes() }
    )
}