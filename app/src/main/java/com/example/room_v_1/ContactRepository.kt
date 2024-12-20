package com.example.room_v_1

import androidx.lifecycle.LiveData

class ContactRepository (private val noteDao: NoteDao) {

    val contacts: LiveData<List<Contact>> = noteDao.getAllNotes()


    suspend fun insert(contact: Contact) { noteDao.insert(contact) }
    suspend fun delete(contact: Contact) { noteDao.delete(contact) }
    suspend fun deleteAll() { noteDao.deleteAll() }
}