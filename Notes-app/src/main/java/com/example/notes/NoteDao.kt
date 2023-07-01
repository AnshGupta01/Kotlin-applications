package com.example.notes

import androidx.room.*

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)
    //Suspend is used to use background processor thread and
    // is called a coroutine in kotlin.

    @Delete
    fun delete(note: Note)

    @Query("SELECT * from notes_table order by id ASC")
    fun getAllNotes(note: Note): List<Note>
}