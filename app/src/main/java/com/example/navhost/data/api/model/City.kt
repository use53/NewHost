package com.example.navhost.data.api.model


import com.google.gson.annotations.SerializedName

data class City(
    val coord: Coord,
    val country: String,
    val id: Double,
    val name: String,
    val population: Double,
    val sunrise: Double,
    val sunset: Double,
    val timezone: Double
)