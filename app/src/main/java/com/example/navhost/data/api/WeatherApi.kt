package com.example.navhost.data.api

import com.example.navhost.data.api.model.ModelApi
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

const val BAZA_URL="http://api.openweathermap.org/"
const val LAT="39.05778"
const val LON="66.83417"
const val APPID="9c57784bf72595c0c518e78437b7c6d1"
const val UNITS="metric"

interface WeatherApi {

    @GET("data/2.5/forecast")
    fun loadApi(
        @Query("lat") lat:String= LAT,
        @Query("lon") lon:String= LON,
        @Query("appid") appid:String= APPID,
        @Query("units") units:String= UNITS
    ):Single<ModelApi>

    companion object{
       private var instanse:WeatherApi?=null
        fun instanse(): WeatherApi? {
            if (instanse==null){

                val retrofit=Retrofit.Builder()
                    .baseUrl(BAZA_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                instanse=retrofit.create(WeatherApi::class.java)
            }else{
                instanse
            }
            return instanse
        }
    }
}