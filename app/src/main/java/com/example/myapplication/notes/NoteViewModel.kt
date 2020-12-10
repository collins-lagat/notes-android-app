package com.example.myapplication.notes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.repository.Note
import com.example.myapplication.repository.NoteDatabase
import com.example.myapplication.repository.NoteRepository

class NoteViewModel(val database: NoteDatabase): ViewModel() {
    private lateinit var repository: NoteRepository
    private lateinit var allNotes: LiveData<List<Note>>
    init {
        repository = NoteRepository(database)
        allNotes = repository.getAllNotes()
    }
    fun getAllNotes(): LiveData<List<Note>> {
        Log.w("Dev", allNotes.value?.size.toString())
        return allNotes
    }

    fun insert(note: Note) {
        repository.insertNote(note)
    }

    fun update(note: Note) {
        repository.updateNote(note)
    }

    fun delete(note: Note) {
        repository.deleteNote(note)
    }

    override fun onCleared() {
        super.onCleared()
        repository.cancel()
    }
}