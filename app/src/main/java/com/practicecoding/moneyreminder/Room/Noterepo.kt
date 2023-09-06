package com.practicecoding.moneyreminder.Room

import androidx.lifecycle.LiveData
import androidx.room.Update

class Noterepo(private val notesdao:Dao) {
    val takenotes:LiveData<List<Note>> = notesdao.getAllUsersInDB()
    suspend fun Insert(note: Note):Long{
       return notesdao.insert(note)
    }
    suspend fun Delete(note: Note) {
        return notesdao.delete(note)
    }
    suspend fun Update(note: Note){
        return notesdao.update(note)
    }
//    suspend fun DeleteAll(note: Note){
//        notesdao.deleteAll()
//    }
}