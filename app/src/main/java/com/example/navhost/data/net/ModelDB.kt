package com.example.navhost.data.net

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class ModelDB (
    val temp:Int,
    val temp_max:Int,
    val temp_min:Int,
    val main:String,
    val description:String,
    val date_dtx:String,
    val date_sub:String,
    @PrimaryKey(autoGenerate = true)
    val it:Long=0
)