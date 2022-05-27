package com.example.notesapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class Note(
    var Title: String,
    var Content: String
) {
    @PrimaryKey(autoGenerate = true)
    var Id: Int = 0
}
