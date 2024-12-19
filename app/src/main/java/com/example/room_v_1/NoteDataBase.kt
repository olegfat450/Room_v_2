package com.example.room_v_1

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Contact::class], version = 1, exportSchema = false)

    abstract  class NoteDataBase: RoomDatabase() {

        abstract fun getNoteDao(): NoteDao
           companion object{

               private var INSTANCE: NoteDataBase? = null
                fun getDataBase(context: Context): NoteDataBase{

                       return INSTANCE ?: synchronized(this) {

                           val instance = Room.databaseBuilder(
                               context = context.applicationContext,
                               NoteDataBase::class.java,
                               name = "note_database"
                           ).build()
                           INSTANCE = instance
                           instance
                       }

                }
           }
}