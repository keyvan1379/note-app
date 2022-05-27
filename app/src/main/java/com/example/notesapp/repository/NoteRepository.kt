package com.example.notesapp.repository

import androidx.lifecycle.LiveData
import com.example.notesapp.database.NoteDatabaseDao
import com.example.notesapp.models.Note

class NoteRepository(private val noteDatabaseDao: NoteDatabaseDao) {

    val allNotes: LiveData<List<Note>> = noteDatabaseDao.getAllNotes()

    suspend fun insert(note: Note) {
        noteDatabaseDao.insert(note)
    }

    suspend fun delete(note: Note) {
        noteDatabaseDao.delete(note)
    }

    suspend fun update(note: Note) {
        noteDatabaseDao.update(note)
    }
}