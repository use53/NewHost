package com.example.navhost.repositoriy

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.navhost.data.net.ModelDB

class MyViewModel ():ViewModel(){
    val lives:MutableLiveData<ModelDB> by lazy { MutableLiveData() }
}