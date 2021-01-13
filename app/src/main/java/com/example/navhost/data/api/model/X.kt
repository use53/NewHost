package com.example.navhost.data.api.model


import com.google.gson.annotations.SerializedName

data class X(
    val clouds: Clouds,
    val dt: Double,
    @SerializedName("dt_txt")
    val dtTxt: String,
    val main: Main,
    val pop: Double,
    val sys: Sys,
    val visibility: Double,
    val weather: List<Weather>,
    val wind: Wind
)