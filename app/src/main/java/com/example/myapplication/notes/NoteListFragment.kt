package com.example.myapplication.notes

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.repository.Note
import com.example.myapplication.repository.NoteDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NoteListFragment : Fragment() {
    private var noteListView: RecyclerView? = null
    private lateinit var noteListAdapter: NoteListAdapter
    private lateinit var noteListViewManager: RecyclerView.LayoutManager
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var notes: LiveData<List<Note>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val floatingActionButton = view.findViewById<FloatingActionButton>(R.id.add_new_note)
        floatingActionButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_noteListFragment_to_newNoteFragment)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val database = NoteDatabase.getInstance(requireContext())

        noteViewModel = ViewModelProvider(this, NoteViewModelFactory(database)).get(NoteViewModel::class.java)
        notes = noteViewModel.getAllNotes()

        noteListViewManager = LinearLayoutManager(requireContext())
        noteListAdapter = NoteListAdapter(notes)
        noteListView = requireView().findViewById<RecyclerView>(R.id.note_list).apply {
            layoutManager = noteListViewManager
            adapter = noteListAdapter
        }

        notes.observe(viewLifecycleOwner, Observer {
            noteListAdapter.setNotes(it)
        })
    }
}