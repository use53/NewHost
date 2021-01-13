package com.example.navhost.data.net

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ModelDB::class],version = 1)
abstract class WeatherDatabes :RoomDatabase(){
    abstract fun weatherDao():WeatherDao
}