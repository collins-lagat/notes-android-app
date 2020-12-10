package com.example.myapplication.notes

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.AppCompatEditText
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.myapplication.R
import com.example.myapplication.repository.Note
import com.example.myapplication.repository.NoteDatabase
import com.google.android.material.snackbar.Snackbar

class NewNoteFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_note, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val cancelButton = requireView().findViewById<Button>(R.id.button_cancel)
        val submitButton = requireView().findViewById<Button>(R.id.button_submit)
        val titleTextView = requireView().findViewById<AppCompatEditText>(R.id.new_note_title)
        val bodyTextView = requireView().findViewById<AppCompatEditText>(R.id.new_note_body)

        val database = NoteDatabase.getInstance(requireContext())
        val noteViewModel = ViewModelProvider(this, NoteViewModelFactory(database)).get(NoteViewModel::class.java)

        submitButton.setOnClickListener {
            requireView().clearFocus()
            if (!TextUtils.isEmpty(titleTextView.text) && !TextUtils.isEmpty(bodyTextView.text)) {
                val note: Note  = Note(
                    0,
                    titleTextView.text.toString(),
                    bodyTextView.text.toString()
                )
                noteViewModel.insert(note)
                Snackbar.make(requireView(), R.string.new_note_added_successfully, 600).show()
                it.findNavController().popBackStack()
            } else {
                Snackbar.make(requireView(), R.string.new_note_error, 600).show()
            }
        }

        cancelButton.setOnClickListener {
            it.findNavController().popBackStack()
        }

    }
}