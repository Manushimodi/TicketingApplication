package com.example.nisargdoshi.ticktingapplication

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import java.text.FieldPosition

class recyclerviewholder(itemview: View) : RecyclerView.ViewHolder(itemview) {
    var storeview :View= itemview
    val tv:TextView = storeview.findViewById(R.id.textView11)


    fun bind(stringdata:String){
        tv.setText(stringdata)
    }
}