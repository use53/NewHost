package com.example.navhost.data.api.model


import com.google.gson.annotations.SerializedName

data class ModelApi(
    val city: City,
    val cnt: Double,
    val cod: String,
    val list: List<X>,
    val message: Double
)