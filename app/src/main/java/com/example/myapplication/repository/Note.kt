package com.example.myapplication.repository

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class Note (
    @PrimaryKey(autoGenerate = true)
    @NonNull
    var id: Int,
    @ColumnInfo var title: String,
    @ColumnInfo var body: String
)

