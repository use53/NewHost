package com.example.navhost.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.navhost.data.net.ModelDB

class ItemAdapCollbaeck :DiffUtil.ItemCallback<ModelDB>(){
    override fun areItemsTheSame(oldItem: ModelDB, newItem: ModelDB): Boolean {
       return oldItem==newItem
    }

    override fun areContentsTheSame(oldItem: ModelDB, newItem: ModelDB): Boolean {
 return oldItem.it==newItem.it
    }
}