package com.example.myapplication.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.*

class NoteRepository(private val database: NoteDatabase) {
    private lateinit var noteDao: NoteDao
    private lateinit var allNotes: LiveData<List<Note>>
    private var job: CompletableJob? = null

    init {
        noteDao = database.noteDao()
        allNotes = noteDao.getAllNotes()
    }

    fun cancel() {
        job?.cancel()
    }

//    Get
    fun getAllNotes(): LiveData<List<Note>> {
        return allNotes
    }

//    Insert
    fun insertNote(note: Note) {
        job = Job()
        job?.let {
            CoroutineScope(Dispatchers.Main + it).launch {
                _handleInsertNote(note)
            }
        }
    }

    private suspend fun _handleInsertNote(note: Note) {
        withContext(Dispatchers.IO) {
            noteDao.insertNote(note)
        }
    }

//    Update
    fun updateNote(note: Note) {
        job = Job()
        job?.let {
            CoroutineScope(Dispatchers.Main + it).launch {
                _handleUpdateNote(note)
            }
        }
    }
    private suspend fun _handleUpdateNote(note: Note) {
        withContext(Dispatchers.IO) {
            noteDao.updateNote(note)
        }
    }


//    Delete
    fun deleteNote(note: Note) {
        job = Job()
        job?.let {
            CoroutineScope(Dispatchers.Main + it).launch {
                _handleDeleteNote(note)
            }
        }
    }
    private suspend fun _handleDeleteNote(note: Note) {
        withContext(Dispatchers.IO) {
            noteDao.deleteNote(note)
        }
    }

}