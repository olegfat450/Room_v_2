package com.example.room_v_1

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModelStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactViewModel (application: Application): AndroidViewModel(application) {

            private val repository: ContactRepository
            public val contacts: LiveData<List<Contact>>

            init{

                  val dao = NoteDataBase.getDataBase(application).getNoteDao()
                  repository = ContactRepository(dao)
                  contacts = repository.contacts
                 }

          fun deleteContact( contact: Contact) = viewModelScope.launch (Dispatchers.IO){ repository.delete(contact) }

          fun insertContact( contact: Contact) = viewModelScope.launch (Dispatchers.IO){ repository.insert(contact) }

          fun deleteAll () = viewModelScope.launch (Dispatchers.IO) { repository.deleteAll() }
}