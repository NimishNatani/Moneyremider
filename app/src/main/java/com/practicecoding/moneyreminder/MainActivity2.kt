package com.practicecoding.moneyreminder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.practicecoding.moneyreminder.Room.Note
import com.practicecoding.moneyreminder.UserInterface.MyViewmodel
import com.practicecoding.moneyreminder.databinding.ActivityMain2Binding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.logging.SimpleFormatter
import kotlin.properties.Delegates

class MainActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding
    lateinit var viewmodel2:MyViewmodel
       var noteAmount:String? = null
    var Amount:Int = 0
    var Noteid:Int=-1
override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
binding = DataBindingUtil.setContentView(this,R.layout.activity_main2)
    viewmodel2= ViewModelProvider(this,
        ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(MyViewmodel::class.java)
    val noteType = intent.getStringExtra("noteType")
    if(noteType.equals("edit"))
    {
        val noteTitle = intent.getStringExtra("noteTitle")
        val noteDescription = intent.getStringExtra("noteDescription")
        val noteAmount1=intent.getStringExtra("noteAmount")
        noteAmount = noteAmount1
        Noteid = intent.getIntExtra("noteId", -1)
        binding.save.setText("Update")
       binding.nameedt.setText(noteTitle)
        binding.amount.setText("Amount: $noteAmount")
        binding.disedt.setText(noteDescription)
    }
    else {
            binding.save.setText("Save")
    }
    binding.plus.setOnClickListener(){
         Amount = (noteAmount?.toInt() ?:0 ) +binding.amountedt.text.toString().toInt()
    }
    binding.minus.setOnClickListener(){
         Amount = (noteAmount?.toInt() ?:0 ) -binding.amountedt.text.toString().toInt()
    }
    binding.save.setOnClickListener(){
        val Notes = binding.nameedt.text.toString()
        val Discription = binding.disedt.text.toString()
      val TotalAmount = Amount.toString()
        if(noteType.equals("edit"))
            {
            if(Notes.isNotEmpty() && Discription.isNotEmpty())
            {
                val sdf = SimpleDateFormat("dd MMM,yyyy - HH:mm")
                val currentdateandTime:String=sdf.format(Date())
                val UpdateNote = Note(Notes,Discription,TotalAmount,currentdateandTime)
                UpdateNote.id = Noteid
                viewmodel2.updatenote(UpdateNote)
                Toast.makeText(this, "Note Updated..", Toast.LENGTH_LONG).show()
            }
            }
        else {
            if (Notes.isNotEmpty() && Discription.isNotEmpty()) {
                val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                val currentDateAndTime: String = sdf.format(Date())
                // if the string is not empty we are calling a
                // add note method to add data to our room database.
                viewmodel2.insertnote(Note(Notes, Discription,TotalAmount, currentDateAndTime))
                Toast.makeText(this, "$Notes Added", Toast.LENGTH_LONG).show()
            }
        }
    }
    startActivity(Intent(applicationContext, MainActivity::class.java))
    this.finish()
}
}