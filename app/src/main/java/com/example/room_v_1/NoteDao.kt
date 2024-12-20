package com.example.room_v_1

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NoteDao {

    @Insert suspend fun insert(note: Contact)

    @Delete suspend fun delete(note: Contact)

    @Query ("SELECT * FROM contact_table ORDER BY id ASC") fun getAllNotes(): LiveData <List<Contact>>

    @Query("DELETE FROM contact_table") fun deleteAll()

}