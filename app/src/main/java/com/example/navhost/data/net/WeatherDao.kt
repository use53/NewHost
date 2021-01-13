package com.example.navhost.data.net

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WeatherDao {

    @Insert
    fun insert(modelDB: ModelDB):Long


    @Query("select * from modeldb where date_sub=:newlist")
    fun allWeather(newlist:String="15:00"):LiveData<MutableList<ModelDB>>

    @Query("select * from modeldb where it limit 8")
    fun aWeather():LiveData<MutableList<ModelDB>>

    @Query("select * from modeldb where it=:newIt")
    fun firstWeather(newIt:Long):LiveData<ModelDB>
}