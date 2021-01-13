package com.example.navhost.adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.navhost.R
import com.example.navhost.data.net.ModelDB
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_layout.*
import java.text.SimpleDateFormat
import java.util.*

class ItemAdapter (ctx: Context):
    ListAdapter<ModelDB,ItemAdapter.ItemVH>(ItemAdapCollbaeck()){

   private val inflater=LayoutInflater.from(ctx)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemVH {
        val view=inflater.inflate(R.layout.item_layout,parent,false)
        return ItemVH(view)
    }

    override fun onBindViewHolder(holder: ItemVH, position: Int) {
        val item=getItem(position)
        holder.onBind(item)
    }

    class ItemVH(override val containerView: View):
        RecyclerView.ViewHolder(containerView),
        LayoutContainer{

       val  simpleDateFormat= SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
      val calendar=Calendar.getInstance()



        fun onBind(model:ModelDB){

            val date=simpleDateFormat.parse(model.date_dtx)
            calendar.time=date
            val days=calendar[Calendar.DAY_OF_WEEK]
            when(days){
                Calendar.SUNDAY->{ item_date.text="Sunday" }
                Calendar.MONDAY->{ item_date.text="Monday" }
                Calendar.TUESDAY->{ item_date.text="Tuesday" }
                Calendar.WEDNESDAY->{ item_date.text="Wednesday" }
                Calendar.THURSDAY->{ item_date.text="Thursday" }
                Calendar.FRIDAY->{ item_date.text="Friday" }
                Calendar.SATURDAY->{ item_date.text="Saturday" }

            }

            if (model.date_dtx.substring(11,16).equals("06:00") ||
                model.date_dtx.substring(11,16).equals("09:00") ||
                model.date_dtx.substring(11,16)=="12:00" ||
                model.date_dtx.substring(11,16)=="15:00"  ||
                model.date_dtx.substring(11,16)=="18:00")
            {
                when(model.main){
                    "Clear"->item_icon.setImageResource(R.drawable.quyosh_24dp)
                    "Snow"->item_icon.setImageResource(R.drawable.snow_24dp)
                    else ->item_icon.setImageResource(R.drawable.blut_24dp)

                }}else{
                when(model.main){
                    "Clear"->item_icon.setImageResource(R.drawable.tun_24dp)
                    "Snow"->item_icon.setImageResource(R.drawable.snow_24dp)
                    else -> item_icon.setImageResource(R.drawable.blut_24dp)
                }
            }

            item_temp.text="${model.temp} C"
        }
    }
}