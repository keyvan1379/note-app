package com.example.notesapp


import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.NavController

import com.example.notesapp.placeholder.PlaceholderContent.PlaceholderItem
import com.example.notesapp.databinding.FragmentNoteBinding
import com.example.notesapp.models.Note
import com.example.notesapp.viewModel.NoteViewModel
import androidx.navigation.fragment.findNavController

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyNotesRecyclerViewAdapter(
    private val values: List<Note>, val viewModel: NoteViewModel, val navController: NavController
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
        holder.deleteBtn.setOnClickListener {
            viewModel.delete(item)
        }
        holder.editBtn.setOnClickListener {
            val bundle = bundleOf("is_new_note" to false, "note_title" to item.Title, "note_content" to item.Content, "note_id" to item.Id)
            navController.navigate(R.id.action_HomePage_to_NotePage, bundle)
        }
    }

    override fun getItemCount(): Int = values.size

    fun update() {
        notifyDataSetChanged()
    }

    inner class ViewHolder(binding: FragmentNoteBinding) : RecyclerView.ViewHolder(binding.root) {
        val noteTitle: TextView = binding.noteTitle
        val noteText: TextView = binding.noteText
        var deleteBtn: Button = binding.deleteBtn
        var editBtn: Button = binding.editBtn

        override fun toString(): String {
            return super.toString() + " '" + noteText.text + "'"
        }
    }

}