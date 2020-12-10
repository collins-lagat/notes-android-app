package com.example.myapplication.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.repository.Note
import kotlinx.android.synthetic.main.note_list_item.view.*

class NoteListAdapter(initalNotes: LiveData<List<Note>>) : RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>() {
    private var notes: List<Note>? = null

    init {
        notes = initalNotes.value
    }

    class NoteViewHolder(val noteView: View) : RecyclerView.ViewHolder(noteView)

    fun setNotes(notes: List<Note>) { this.notes = notes; }

    override fun getItemCount(): Int {
        return notes?.size ?: 0
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val noteView = LayoutInflater.from(parent.context).inflate(R.layout.note_list_item, parent, false) as View
        return NoteViewHolder(noteView)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.noteView.apply {
            note_title.text = requireNotNull(notes)[position].title
            note_body.text = requireNotNull(notes)[position].body
        }
    }
}