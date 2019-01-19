
package com.example.nisargdoshi.ticktingapplication
import android.app.Activity
import android.app.PendingIntent.getActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nisargdoshi.ticktingapplication.R
import com.example.nisargdoshi.ticktingapplication.recyclerviewholder
import com.example.nisargdoshi.ticktingapplication.superadmin_homepage

import java.util.ArrayList
import java.util.zip.Inflater

class  recyclerviewadater(activity: Activity,data1:ArrayList<String>): RecyclerView.Adapter<recyclerviewholder>() {

      var activity=activity
      var data=data1

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): recyclerviewholder {
        //val inflate =
       // var view:View
       val layoutInflater:LayoutInflater = LayoutInflater.from(activity)
        // Inflate the layout using LayoutInflater
        val view: View = layoutInflater.inflate(
            R.layout.layout, // Custom view/ layout
            p0, // Root layout to attach the view
            false )// Attach with root layout or not)
        return recyclerviewholder(view)
           }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(p0: recyclerviewholder, p1: Int) {
        p0.bind(data.get(p1))
       }

}
