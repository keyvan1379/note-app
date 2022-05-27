package com.example.notesapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val Id: Int,
    val Title: String,
    val Content: String
)
