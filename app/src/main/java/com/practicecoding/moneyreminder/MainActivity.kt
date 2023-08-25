package com.practicecoding.moneyreminder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.practicecoding.moneyreminder.Room.Note
import com.practicecoding.moneyreminder.Room.NoteDatabase
import com.practicecoding.moneyreminder.UserInterface.MyViewmodel
import com.practicecoding.moneyreminder.UserInterface.Myadapter
import com.practicecoding.moneyreminder.UserInterface.NoteClickDeleteInterface
import com.practicecoding.moneyreminder.UserInterface.NoteClickInterface
import com.practicecoding.moneyreminder.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), NoteClickInterface, NoteClickDeleteInterface {
    lateinit var viewmodel: MyViewmodel
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
    viewmodel= ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(MyViewmodel::class.java)
   val myadapter = Myadapter(this,this,this)
    binding.recycleview1.layoutManager = LinearLayoutManager(this)
        binding.recycleview1.adapter = myadapter
        viewmodel.allNotes.observe(this, Observer { list ->
            list?.let {
                // on below line we are updating our list.
                myadapter.updatelist(it)
            }
        })
        binding.fabbtn.setOnClickListener(){
            val intent = Intent(this@MainActivity, MainActivity2::class.java)
            startActivity(intent)
        }
    }
    override fun onNoteClick(note: Note) {
        // opening a new intent and passing a data to it.
        val intent = Intent(this@MainActivity, MainActivity2::class.java)
        intent.putExtra("noteType", "Edit")
        intent.putExtra("noteTitle", note.name)
        intent.putExtra("noteAmount",note.amount)
        intent.putExtra("noteDescription", note.discription)
        intent.putExtra("noteId", note.id)
        startActivity(intent)
        this.finish()
    }

    override fun onDeleteIconClick(note: Note) {
        // in on note click method we are calling delete
        // method from our view modal to delete our not.
        viewmodel.deletenote(note)
        // displaying a toast message
        Toast.makeText(this, "${note.name} Deleted", Toast.LENGTH_LONG).show()
    }
}