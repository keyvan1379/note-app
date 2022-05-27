package com.example.notesapp

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.notesapp.placeholder.PlaceholderContent.PlaceholderItem
import com.example.notesapp.databinding.FragmentNoteBinding
import com.example.notesapp.models.Note

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyNotesRecyclerViewAdapter(
    private val values: List<Note>
) : RecyclerView.Adapter<MyNotesRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        print("view holder done")
        return ViewHolder(
            FragmentNoteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.noteTitle.text = item.Title
        holder.noteText.text = item.Content
    }

    override fun getItemCount(): Int = values.size


    inner class ViewHolder(binding: FragmentNoteBinding) : RecyclerView.ViewHolder(binding.root) {
        val noteTitle: TextView = binding.noteTitle
        val noteText: TextView = binding.noteText

        override fun toString(): String {
            return super.toString() + " '" + noteText.text + "'"
        }
    }

}