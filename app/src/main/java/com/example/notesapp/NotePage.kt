package com.example.notesapp

import android.app.Application
import android.os.Bundle
import android.provider.ContactsContract
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.notesapp.databinding.ActivityMainBinding
import com.example.notesapp.databinding.FragmentHomePageBinding
import com.example.notesapp.databinding.FragmentNotePageBinding
import com.example.notesapp.models.Note
import com.example.notesapp.viewModel.NoteViewModel
import com.google.android.material.snackbar.Snackbar

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NotePage.newInstance] factory method to
 * create an instance of this fragment.
 */
class NotePage : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentNotePageBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentNotePageBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val isNewNote = arguments?.getBoolean("is_new_note", true)
        val noteTitle = arguments?.getString("note_title", "")
        val noteContent = arguments?.getString("note_content", "")
        val noteId = arguments?.getInt("note_id", -1)

        if (isNewNote == false)
        {
            binding.titleTextField.editText?.text = Editable.Factory.getInstance().newEditable(noteTitle)
            binding.textField.editText?.text = Editable.Factory.getInstance().newEditable(noteContent)
        }

        var noteViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(
            Application())
        ).get(NoteViewModel::class.java)

        binding.saveBtn.setOnClickListener {
            // Get vals
            val newTitle = binding.titleTextField.editText?.text.toString()
            val newContent = binding.textField.editText?.text.toString()

            var canProceed = true

            if (newTitle.isEmpty())
            {
                binding.titleTextField.error = "Title cannot be empty"
                canProceed = false
            }
            else if (newContent.isEmpty())
            {
                binding.textField.error = "Content cannot be empty"
                canProceed = false
            }

            if (isNewNote == true)
            {
                if (canProceed)
                {
                    val note = Note(newTitle,newContent)
                    noteViewModel.insert(note)
                    Snackbar.make(it, "Note Created", Snackbar.LENGTH_LONG).show()
                }
            }
            else
            {
                if (canProceed)
                {
                    val note = Note(newTitle,newContent)
                    // set Id for update note
                    note.Id = noteId!!
                    noteViewModel.update(note)
                    Snackbar.make(it, "Note Updated", Snackbar.LENGTH_LONG).show()
                }
            }

            if (canProceed)
                findNavController().navigate(R.id.action_NotePage_to_HomePage)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NotePage.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NotePage().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}