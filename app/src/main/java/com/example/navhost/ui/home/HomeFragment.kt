package com.example.navhost.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.navhost.R
import com.example.navhost.data.api.WeatherApi
import com.example.navhost.data.net.DBProviders
import com.example.navhost.data.net.ModelDB
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_home.*
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {

    private val api by lazy(LazyThreadSafetyMode.NONE) { WeatherApi.instanse() }
    private val db by lazy(LazyThreadSafetyMode.NONE) { context?.let { DBProviders.instanse(it) } }
   private var date:Date?=null
    private var format:SimpleDateFormat?=null
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        date= Date()
        format= SimpleDateFormat("HH:mm")
        db?.firstWeather(1)?.observe(viewLifecycleOwner, {
         if (it!=null){
             newAdd(it)
         }else{
             Single.fromCallable {
                try {
                    val newapi=api!!.loadApi().blockingGet()
                    val modelDB=ModelDB(newapi.list[0].main.temp.toInt(),
                        newapi.list[0].main.tempMax.toInt(),
                        newapi.list[0].main.tempMin.toInt(),
                        newapi.list[0].weather[0].main,
                        newapi.list[0].weather[0].description,
                        newapi.list[0].dtTxt,
                        newapi.list[0].dtTxt.substring(12,16))
                    modelDB
                }catch (e:Exception){
                    ModelDB(1,1,1,
                        "no","no",
                        "dyugyugyugkuygug","3")
                }
                 }
                 .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(Consumer {
                    newAdd(it)
                 }, Consumer {})



         }
        })

    }

    private fun newAdd(modelDB: ModelDB) {
        HH_mm.text=modelDB.temp.toString()
        temp_max.text=modelDB.temp_max.toString()
        temp_min.text=modelDB.temp_min.toString()
        temp_date.text=format?.format(date)
        if (modelDB.date_dtx.substring(11,16).equals("06:00") ||
            modelDB.date_dtx.substring(11,16).equals("09:00") ||
            modelDB.date_dtx.substring(11,16).equals("12:00") ||
            modelDB.date_dtx.substring(11,16).equals("15:00")  ||
            modelDB.date_dtx.substring(11,16).equals("18:00"))
        {
            when(modelDB.main){
            "Clear"->temp_icon.setImageResource(R.drawable.quyosh_24dp)
            "Snow"->temp_icon.setImageResource(R.drawable.snow_24dp)
                else ->temp_icon.setImageResource(R.drawable.blut_24dp)

        }}else{
            when(modelDB.main){
                "Clear"->temp_icon.setImageResource(R.drawable.tun_24dp)
                "Snow"->temp_icon.setImageResource(R.drawable.snow_24dp)
                else -> temp_icon.setImageResource(R.drawable.blut_24dp)
            }
        }
    }
}
