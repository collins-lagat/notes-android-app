package com.example.myapplication.repository

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(vararg note: Note)

    @Update
    fun updateNote(vararg note: Note)

    @Delete
    fun deleteNote(vararg  note: Note)

    @Query("DELETE FROM note_table")
    fun deleteAll()

    @Query("SELECT * FROM note_table ORDER BY id ASC")
    fun getAllNotes(): LiveData<List<Note>>
}