package com.example.room_v_1

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NoteDao {

    @Insert suspend fun insert(note: Contact)

    @Delete suspend fun delete(note: Contact)

    @Query ("SELECT * FROM notes_table ORDER BY id ASC") fun getAllNotes(): List<Contact>

    @Query("DELETE FROM notes_table") fun deleteAll()

}