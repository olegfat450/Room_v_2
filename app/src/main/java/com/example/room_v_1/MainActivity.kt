package com.example.room_v_1

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {

         private lateinit var viewModel: ContactViewModel

         private lateinit var toolbar: Toolbar
         private lateinit var nameText: EditText
         private lateinit var surnameText: EditText
         private lateinit var phoneText: EditText
         private lateinit var button: Button
         private lateinit var listTv: RecyclerView

          var list: MutableList<Contact> = mutableListOf()
          val adapter = ContactAdapter(list)

    @SuppressLint("MissingInflatedId", "NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val formatDate = SimpleDateFormat("dd MMMM yyyy    HH : mm", Locale.getDefault())

        init()

        viewModel.contacts.observe(this, { list.clear(); list.addAll(it); adapter.notifyDataSetChanged() })



           button.setOnClickListener {

                 if (nameText.text.isEmpty() and surnameText.text.isEmpty()) return@setOnClickListener

              val listtemp = Contact(nameText.text.toString(),surnameText.text.toString(),phoneText.text.toString(),formatDate.format(Date()).toString())
                                     nameText.text.clear(); surnameText.text.clear(); phoneText.text.clear()

                         viewModel.insertContact(listtemp)

                         list.add(listtemp);adapter.notifyDataSetChanged() }

        adapter.setOnDelClick(object: ContactAdapter.OnDelClick{

            override fun onDelClick (position: Int){

                val builder = AlertDialog.Builder(this@MainActivity)
                     builder.setTitle("Удалить запись?")
                         .setPositiveButton("Удалить"){ _,_ -> viewModel.deleteContact(list[position])
                             list.removeAt(position); adapter.notifyDataSetChanged();}
                             .setNegativeButton("Отмена") { _,_ -> }.create()
                    builder.show() } })
    }





    private fun init() {

      //  db = NoteDataBase.getDataBase(this)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        title = "Контакты"
        toolbar.setTitleTextColor(Color.BLUE)
        toolbar.titleMarginStart = 200
        toolbar.setBackgroundColor(Color.GRAY)
        nameText = findViewById(R.id.nameText)
        surnameText = findViewById(R.id.surnameText)
        phoneText = findViewById(R.id.phoneText)
        button = findViewById(R.id.button)
        listTv = findViewById(R.id.listTv)
        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application))[ContactViewModel::class.java]
        listTv.layoutManager = LinearLayoutManager(this)

        listTv.adapter = adapter
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_exit,menu)
        return super.onCreateOptionsMenu(menu) }

    @SuppressLint("NotifyDataSetChanged")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
                            R.id.exit -> finishAffinity()
                            R.id.clear -> { list.clear(); adapter.notifyDataSetChanged(); viewModel.deleteAll() }
                          }
        return super.onOptionsItemSelected(item) }

  //  private fun addNote(db: NoteDataBase, note: Contact) = CoroutineScope(Dispatchers.IO).async { db.getNoteDao().insert(note) }

  //  private fun readDatabase(db: NoteDataBase) = CoroutineScope(Dispatchers.IO).async { list.addAll(db.getNoteDao().getAllNotes()) }

  //  private fun removeAll(db: NoteDataBase) = CoroutineScope(Dispatchers.IO).async { db.getNoteDao().deleteAll() }

  //  private fun delNote(db: NoteDataBase, note: Contact) = CoroutineScope(Dispatchers.IO).async { db.getNoteDao().delete(note) }

}