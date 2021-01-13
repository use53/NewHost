package com.example.navhost.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.navhost.MainActivity
import com.example.navhost.R
import com.example.navhost.adapter.ItemAdapter
import com.example.navhost.adapter.gor.GorAdapter
import com.example.navhost.data.net.DBProviders
import com.example.navhost.data.net.ModelDB
import com.example.navhost.onItemClickFragment
import com.example.navhost.repositoriy.IonClickListener
import com.example.navhost.repositoriy.MyViewModel
import kotlinx.android.synthetic.main.fragment_dashboard.*
import java.text.SimpleDateFormat
import java.util.*

class DashboardFragment : Fragment(){

    private val itemAdapter by lazy(LazyThreadSafetyMode.NONE) { context?.let { ItemAdapter(it) } }
    private val dao by lazy(LazyThreadSafetyMode.NONE) { context?.let { DBProviders.instanse(it) } }
    private val gorAdapter by lazy(LazyThreadSafetyMode.NONE) { context?.let { GorAdapter(it) } }

    private var isConsel=false

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addAdapter()
        goritemAdapter()
    }

    private fun goritemAdapter() {
        gor_rec.adapter=gorAdapter
        fab.setOnClickListener {
            if (isConsel){
          gor_rec.layoutManager=LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
            }else{
                gor_rec.layoutManager=GridLayoutManager(context,3)
            }
            isConsel=!isConsel
        }
        dao?.aWeather()?.observe(viewLifecycleOwner, { gorAdapter!!.submitList(it) })
    }

    private fun addAdapter() {
        item_rec.adapter=itemAdapter

        item_rec.addItemDecoration(DividerItemDecoration(context,DividerItemDecoration.VERTICAL))
         dao!!.allWeather().observe(viewLifecycleOwner, { itemAdapter!!.submitList(it) })
    }



}
