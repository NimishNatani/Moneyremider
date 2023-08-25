package com.practicecoding.moneyreminder.UserInterface

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.practicecoding.moneyreminder.Room.Note
import com.practicecoding.moneyreminder.Room.NoteDatabase
import com.practicecoding.moneyreminder.Room.Noterepo
import kotlinx.coroutines.launch

class MyViewmodel(application: Application):AndroidViewModel(application) {
   lateinit var  allNotes : LiveData<List<Note>>
  lateinit var   repository:Noterepo
  init {
      val dao=NoteDatabase.getinstance(application).getNotes()
      repository = Noterepo(dao)
      allNotes = repository.takenotes
  }
    fun insertnote(note: Note) = viewModelScope.launch { repository.Insert(note) }
    fun updatenote(note: Note) = viewModelScope.launch { repository.Update(note) }
    fun deletenote(note: Note) = viewModelScope.launch { repository.Delete(note) }
//    fun deleteallnote(note: Note) = viewModelScope.launch { repository.DeleteAll(note) }
}