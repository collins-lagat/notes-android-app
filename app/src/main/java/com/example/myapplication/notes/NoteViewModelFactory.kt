package com.example.myapplication.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.repository.NoteDatabase

class NoteViewModelFactory(private val database: NoteDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
            return NoteViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}