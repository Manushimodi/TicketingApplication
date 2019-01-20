package com.example.nisargdoshi.ticktingapplication

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.example.nisargdoshi.ticktingapplication.Model.ticketmodel
import java.text.FieldPosition

class recyclerviewholder(itemview: View) : RecyclerView.ViewHolder(itemview) {
    var storeview :View= itemview
    val textviewid:TextView = storeview.findViewById(R.id.tv_ticketno_ticketlayout)
    val textviewtitle:TextView = storeview.findViewById(R.id.tv_tickettitle_ticketlayout)
    val textviewdescription:TextView = storeview.findViewById(R.id.tv_ticketdesc_ticketlayout)
    val textviewcategory:TextView = storeview.findViewById(R.id.tv_ticketcategory_ticketlayout)
    val textviewdepartment:TextView = storeview.findViewById(R.id.tv_ticketdepartment_ticketlayout)
    val textviewdate:TextView = storeview.findViewById(R.id.tv_ticketdate_ticketlayout)
    val textviewtime:TextView = storeview.findViewById(R.id.tv_tickettime_ticketlayout)


    fun bind(modeldata:ticketmodel){
        textviewid.setText(modeldata.ticketid)
        textviewtitle.setText(modeldata.tickettitle)
        textviewcategory.setText(modeldata.ticketcategory)
        textviewdepartment.setText(modeldata.ticketdepartment)
        textviewdescription.setText(modeldata.ticketdesc)
        textviewdate.setText(modeldata.ticketdate)
        textviewtime.setText(modeldata.tickettime)

    }
}