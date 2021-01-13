package com.example.navhost.repositoriy

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast
import com.example.navhost.R
import com.example.navhost.data.api.WeatherApi
import com.example.navhost.data.api.model.ModelApi
import com.example.navhost.data.net.ModelDB
import com.example.navhost.data.net.WeatherDao
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import java.lang.Exception

class RopoImpe(val weatherApi:WeatherApi,val db:WeatherDao, ctx:Context) :IRepository{

   private var diolog:AlertDialog?=null
    private val iOnItem =ctx as IOnItem

      override fun exsampleFirst(ctx:Context) {
          iOnItem.firstProgres()
       Single.fromCallable {
          val vm= itemApi()
             try {
                   vm!!.list.forEach {
                         val modelDB=ModelDB(it.main.temp.toInt()
                             ,it.main.tempMax.toInt()
                         ,it.main.tempMin.toInt(),
                         it.weather[0].main,
                         it.weather[0].description,
                         it.dtTxt,
                         it.dtTxt.substring(11,16))
                       db.insert(modelDB)
                   }
             }catch (e:Exception){
             }finally {

             }
       }
             .subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread())
             .subscribe(Consumer {iOnItem.cansel() }, Consumer { iOnItem.inFlags() })
      }


    private fun itemApi(): ModelApi? {
        val vm=weatherApi.loadApi().blockingGet()
        return vm
    }
}