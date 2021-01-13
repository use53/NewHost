package com.example.navhost.adapter.gor


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.navhost.MainActivity
import com.example.navhost.R
import com.example.navhost.adapter.ItemAdapCollbaeck
import com.example.navhost.data.net.ModelDB
import com.example.navhost.repositoriy.IonClickListener
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.gorizantal_layout.*

class GorAdapter (ctx:Context):ListAdapter<ModelDB,GorAdapter.Vh>(ItemAdapCollbaeck()){

     private val infalater=LayoutInflater.from(ctx)
    private val listener by lazy(LazyThreadSafetyMode.NONE) {ctx as IonClickListener }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        val gorView=infalater.inflate(R.layout.gorizantal_layout,parent,false)
        return Vh(gorView,listener)
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        val itm=getItem(position)
        holder.onBind(itm)
    }

    class Vh(override val containerView: View,val listener: IonClickListener)
        :RecyclerView.ViewHolder(containerView),LayoutContainer{
        var liste:ModelDB?=null
        init {
            containerView.setOnClickListener {
                liste?.let { it1 -> listener.onClick(it1) }
            }
        }

        fun  onBind(modelDB: ModelDB){
            liste=modelDB
            gor_HH.text=modelDB.date_dtx.substring(11,16)
            gor_temp.text="${modelDB.temp} C"

            if (modelDB.date_dtx.substring(11,16)=="06:00" ||
                modelDB.date_dtx.substring(11,16)=="09:00" ||
                modelDB.date_dtx.substring(11,16)=="12:00" ||
                modelDB.date_dtx.substring(11,16)=="15:00"  ||
                modelDB.date_dtx.substring(11,16)=="18:00")
            {
                when(modelDB.main){
                    "Clear"->gor_icon.setImageResource(R.drawable.quyosh_24dp)
                    "Snow"->gor_icon.setImageResource(R.drawable.snow_24dp)
                    else ->gor_icon.setImageResource(R.drawable.blut_24dp)

                }}else{
                when(modelDB.main){
                    "Clear"->gor_icon.setImageResource(R.drawable.tun_24dp)
                    "Snow"->gor_icon.setImageResource(R.drawable.snow_24dp)
                    else -> gor_icon.setImageResource(R.drawable.blut_24dp)
                }
            }
        }
    }
}