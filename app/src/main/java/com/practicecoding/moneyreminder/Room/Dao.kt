package com.practicecoding.moneyreminder.Room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface Dao {
    @Insert
    suspend fun insert(note:Note):String
    @Delete
    suspend fun delete(note: Note):String
    @Update
    suspend fun update(note: Note):String
//    @Query("DELETE FROM Notestable")
//    suspend fun deleteAll()

    @Query("SELECT * FROM Notestable order by id ASC")
    fun getAllUsersInDB(): LiveData<List<Note>>
}