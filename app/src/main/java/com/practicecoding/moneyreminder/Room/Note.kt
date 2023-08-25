package com.practicecoding.moneyreminder.Room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Notestable")
data class Note(
    @ColumnInfo(name = "name")
    val name:String,
    @ColumnInfo(name = "discription")
    val discription:String,
    @ColumnInfo(name = "amount")
    val amount:String,
    @ColumnInfo(name = "timestamp")
    val timestamp:String,
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
)
