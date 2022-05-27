package com.example.notesapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.LiveData
import com.example.notesapp.database.NoteDatabase
import com.example.notesapp.models.Note
import com.example.notesapp.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application): AndroidViewModel(application) {

    private val repo: NoteRepository
    private val allNotes: LiveData<List<Note>>

    init {
        val dao = NoteDatabase.getInstance(application).noteDatabaseDao()
        repo = NoteRepository(dao)
        allNotes = repo.allNotes
    }


    fun insert(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repo.insert(note)
    }

    fun delete(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repo.delete(note)
    }

    fun update(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repo.update(note)
    }

    fun getNotes(): LiveData<List<Note>> {
        return allNotes
    }
}