package com.example.notesapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.notesapp.databinding.FragmentHomePageBinding
import com.example.notesapp.databinding.FragmentNoteListBinding
import com.example.notesapp.models.Note
import com.example.notesapp.placeholder.PlaceholderContent

/**
 * A fragment representing a list of Items.
 */
class NotesFragment : Fragment() {

    private var columnCount = 1
    private lateinit var notesList : ArrayList<Note>
    private var _binding: FragmentNoteListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        notesList = ArrayList()
        notesList.add(Note("title", "content"))
        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
        print("notes done")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        _binding = FragmentNoteListBinding.inflate(inflater, container, false)
//        val view = binding.root
//        return view

        val view = inflater.inflate(R.layout.fragment_note_list, container, false)
        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager =  LinearLayoutManager(context)
                adapter = MyNotesRecyclerViewAdapter(notesList)
            }
        }
//        recyclerView = view.findViewById(R.id.list)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        print("hi")
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            NotesFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}