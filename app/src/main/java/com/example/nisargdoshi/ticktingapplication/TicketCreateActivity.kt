package com.example.nisargdoshi.ticktingapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ServerValue
import kotlinx.android.synthetic.main.activity_ticket_create.*
import kotlinx.android.synthetic.main.ticketlayout.*

class TicketCreateActivity : AppCompatActivity() {
    private var mAuth: FirebaseAuth? = null
    private var mDatabaseReference: DatabaseReference? = null
    private var mDatabase: FirebaseDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket_create)



        mDatabase = FirebaseDatabase.getInstance()
        mDatabaseReference = mDatabase!!.reference!!.child("organizations")
        mAuth = FirebaseAuth.getInstance()

        var list_of_items = arrayOf("Item 1", "Item 2", "Item 3")
        var arrayList: ArrayList<String>? = ArrayList<String>()

        arrayList!!.add("manushi")
        arrayList!!.add("manushi")
        arrayList!!.add("manushi")
        arrayList!!.add("manushi")
        val array_adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, list_of_items)
        array_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        sp_ticketcategory_ticketcreate!!.setAdapter(array_adapter)


        var array_adapter1 = ArrayAdapter(this,android.R.layout.simple_spinner_item, arrayList)
        array_adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        sp_ticketdepartment_ticketcreate!!.setAdapter(array_adapter1)


        btn_ticketcreate_ticketcreate.setOnClickListener(){
            var mDatabase1: DatabaseReference? = null
            var mDatabase2: DatabaseReference? = null

            mDatabase2 = mDatabase!!.reference!!.child("organizations/" + mAuth!!.uid + "/tickets")
            mDatabase2!!.child("ticketno").setValue("00001")
            mDatabase2!!.child("tickeettitle").setValue(et_tickettitle_ticketcreate.text.toString())
            mDatabase2!!.child("ticketdescription").setValue(et_ticketdesc_ticketcreate.text.toString())
            mDatabase2!!.child("tickecategory").setValue(sp_ticketcategory_ticketcreate.selectedItem.toString())
            mDatabase2!!.child("ticketdepartment").setValue(sp_ticketdepartment_ticketcreate.selectedItem.toString())
            mDatabase2!!.child("ticketcreationtime").setValue(ServerValue.TIMESTAMP);

        }

    }
}
