package com.practicecoding.moneyreminder.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase:RoomDatabase() {
    abstract fun getNotes():Dao
    companion object{
        @Volatile
        private var INSTANCE:NoteDatabase?=null
        fun getinstance(context: Context):NoteDatabase{
            return INSTANCE?: synchronized(this){
                var instance =Room.databaseBuilder(context.applicationContext,
    NoteDatabase::class.java,"note_db").build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

}