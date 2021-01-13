package com.example.navhost

import android.os.Bundle
import android.view.WindowManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.navhost.data.api.WeatherApi
import com.example.navhost.data.net.DBProviders
import com.example.navhost.data.net.ModelDB
import com.example.navhost.repositoriy.*
import com.example.navhost.ui.First_Progress

class MainActivity : AppCompatActivity() ,IOnItem,IonClickListener{

    val api by lazy(LazyThreadSafetyMode.NONE) { WeatherApi.instanse() }
     val vm by lazy { ViewModelProvider(this)[MyViewModel::class.java] }
    val dao by lazy(LazyThreadSafetyMode.NONE) { DBProviders.instanse(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
            navView.setupWithNavController(navController)

        val repo: IRepository = RopoImpe(api!!,dao!!,this)
        repo.exsampleFirst(this)
    }

    override fun inFlags() {
        supportFragmentManager.beginTransaction()
            .add(R.id.container,ProgressFragment())
            .addToBackStack(null)
            .commit()

    }

    override fun cansel() {
        onBackPressed()
    }

    override fun firstProgres() {
        supportFragmentManager.beginTransaction()
            .add(R.id.container,First_Progress())
            .addToBackStack(null)
            .commit()
    }

    override fun onClick(newid: ModelDB) {
        vm.lives.postValue(newid)

        supportFragmentManager.
        beginTransaction().
        add(R.id.nav_host_fragment,onItemClickFragment())
            .addToBackStack(null)
            .commit()
    }
}
