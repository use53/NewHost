package com.example.navhost

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.navhost.data.net.DBProviders
import com.example.navhost.repositoriy.MyViewModel
import kotlinx.android.synthetic.main.fragment_click.*

class onItemClickFragment : Fragment() {

   private val daos by lazy { context?.let { DBProviders.instanse(it) } }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_click, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val vm= activity?.let { ViewModelProvider(it)[MyViewModel::class.java] }
        vm?.lives?.observe(viewLifecycleOwner, Observer {
             clickHH_mm.text=it.temp.toString()
             clicktemp_max.text=it.temp_max.toString()
             clicktemp_min.text=it.temp_min.toString()
             clicktemp_date.text=it.date_dtx.substring(11,16)
             if (it.date_dtx.substring(11,16).equals("06:00") ||
                 it.date_dtx.substring(11,16).equals("09:00") ||
                 it.date_dtx.substring(11,16).equals("12:00") ||
                 it.date_dtx.substring(11,16).equals("15:00")  ||
                 it.date_dtx.substring(11,16).equals("18:00"))
             {
                 when(it.main){
                     "Clear"->clicktemp_icon.setImageResource(R.drawable.quyosh_24dp)
                     "Snow"->clicktemp_icon.setImageResource(R.drawable.snow_24dp)
                     else ->clicktemp_icon.setImageResource(R.drawable.blut_24dp)

                 }}else{
                 when(it.main){
                     "Clear"->clicktemp_icon.setImageResource(R.drawable.tun_24dp)
                     "Snow"->clicktemp_icon.setImageResource(R.drawable.snow_24dp)
                     else -> clicktemp_icon.setImageResource(R.drawable.blut_24dp)
                 }
             }
         })

    }

}
