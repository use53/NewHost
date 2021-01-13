package com.example.navhost.data.net

import android.content.Context
import androidx.room.Room

class DBProviders {

    companion object{
        private var instanse:WeatherDao?=null
         fun instanse(ctx: Context): WeatherDao? {
             if (instanse == null) {
                 val room=Room.inMemoryDatabaseBuilder(ctx,WeatherDatabes::class.java)
                     .allowMainThreadQueries()
                     .build()
                 instanse=room.weatherDao()
             }else{
                 instanse
             }
             return instanse
         }
    }
}