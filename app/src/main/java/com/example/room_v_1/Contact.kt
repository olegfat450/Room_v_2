package com.example.room_v_1

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact_table")
 data class Contact

    ( @ColumnInfo(name = "name")val name: String,@ColumnInfo(name = "surname")val surname: String,
      @ColumnInfo(name = "phone")val phone: String,@ColumnInfo(name = "date")val date: String){

     @PrimaryKey(autoGenerate = true)var id = 0


}