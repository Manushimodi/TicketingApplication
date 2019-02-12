package com.example.nisargdoshi.ticktingapplication

import android.annotation.SuppressLint
import android.app.ActionBar
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat

import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_superadmin_homepage.*
import kotlinx.android.synthetic.main.app_bar_superadmin_homepage.*
import android.support.v7.widget.SearchView.SearchAutoComplete
import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.*
import android.widget.*
import com.example.nisargdoshi.ticktingapplication.Model.departmentmodel
import com.example.nisargdoshi.ticktingapplication.Model.ticketmodel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.content_superadmin_homepage.*
import kotlinx.android.synthetic.main.department_dialoague.*
import java.util.*
import kotlin.collections.ArrayList


class superadmin_homepage : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var dataarray: ArrayList<ticketmodel>? = ArrayList<ticketmodel>()
    private var mAuth: FirebaseAuth? = null
    private var mDatabaseReference: DatabaseReference? = null
    private var mDatabase: FirebaseDatabase? = null
    var departmentmodel: ArrayList<departmentmodel>? = ArrayList<departmentmodel>()

    // lateinit var actity:Activity


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_superadmin_homepage)
        setSupportActionBar(toolbar)
        mDatabase = FirebaseDatabase.getInstance()
        mDatabaseReference = mDatabase!!.reference!!.child("organizations")
        mAuth = FirebaseAuth.getInstance()

        //val timestampNow = HashMap<String,Object>()
      //  timestampNow.put("timestamp", ServerValue.TIMESTAMP);


    //    Log.d("date----","==="+ ServerValue.TIMESTAMP.)
        /*var ti=ticketmodel()
        ti.ticketcategory="yoooo"
        Log.d("modeldata==","***"+ti.ticketcategory+"=="+ti.ticketid)
*/


       // actity=this
        fab.setOnClickListener { root1 ->
            var intent = Intent(this,TicketCreateActivity::class.java)
            startActivity(intent)




        }


        var ti=ticketmodel()
        ti.ticketid="#00001"
        ti.tickettitle="Problem in manushi"
        ti.ticketdesc="she has more mind"
        ti.ticketdepartment="Crime"
        ti.ticketcategory="thief"
        ti.ticketdate="20/01/2019"
        ti.tickettime="02:29 PM"

        var ti1=ticketmodel()
        ti1.ticketid="#00002"
        ti1.tickettitle="Problem in manushi"
        ti1.ticketdesc="she has more mind"
        ti1.ticketdepartment="Crime"
        ti1.ticketcategory="thief"
        ti1.ticketdate="20/01/2019"
        ti1.tickettime="02:29 PM"

        var ti2=ticketmodel()
        ti2.ticketid="#00003"
        ti2.tickettitle="Problem in manushi"
        ti2.ticketdesc="she has more mind"
        ti2.ticketdepartment="Crime"
        ti2.ticketcategory="thief"
        ti2.ticketdate="20/01/2019"
        ti2.tickettime="02:29 PM"


        dataarray!!.add(ti)
        dataarray!!.add(ti1)
        dataarray!!.add(ti2)
        dataarray!!.add(ticketmodel())

        val adapter =  recyclerviewadater(this,dataarray!!)
        val layoutmanager:RecyclerView.LayoutManager=LinearLayoutManager(this@superadmin_homepage)
        recyclerview.layoutManager=layoutmanager
        recyclerview.adapter=adapter
        adapter.notifyDataSetChanged()




        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)


        //sv_serchhome_superadminhome.setLayoutParams(supportActionBar.LayoutParams(Gravity.RIGHT))

    }



    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    @SuppressLint("RestrictedApi")
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.superadmin_homepage, menu)
        var searchitem= menu.findItem(R.id.action_search)
        var searchview= searchitem.actionView as SearchView


       /* searchview.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                Log.d("searchtext==","***"+p0)
                if(p0.equals("") ||p0==null){
                    recyclerview.visibility=View.GONE
                }
                else{
                    recyclerview.visibility=View.VISIBLE
                    var temparray:ArrayList<String> = ArrayList<String>()
                   // temparray=dataarray!!
                    for(i in 0..(dataarray!!.size-1)){
                        if(dataarray!!.get(i).contains(p0,ignoreCase = true)){
                            temparray.add(dataarray!!.get(i))
                        }
                    }
                    val adapter =  recyclerviewadater(this@superadmin_homepage,temparray!!)
                    val layoutmanager:RecyclerView.LayoutManager=GridLayoutManager(this@superadmin_homepage,3)
                    recyclerview.layoutManager=layoutmanager
                    recyclerview.adapter=adapter
                    adapter.notifyDataSetChanged()

                }
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                Log.d("searchtextchange==","***"+p0)

                var temparray:ArrayList<String> = ArrayList<String>()
                if(p0.equals("") ||p0==null){
                    temparray=dataarray!!
                }
                else {
                    recyclerview.visibility = View.VISIBLE
                    // temparray=dataarray!!
                    for (i in 0..(dataarray!!.size - 1)) {
                        if (dataarray!!.get(i).contains(p0, ignoreCase = true)) {
                            temparray.add(dataarray!!.get(i))
                        }
                    }
                }
                    val adapter = recyclerviewadater(this@superadmin_homepage, temparray!!)
                    val layoutmanager: RecyclerView.LayoutManager = LinearLayoutManager(this@superadmin_homepage)
                    recyclerview.layoutManager = layoutmanager
                    recyclerview.adapter = adapter
                    adapter.notifyDataSetChanged()
                    return true
                }
        })
*/
/*
        var mSearchAutoComplete =
            searchview.findViewById(android.support.v7.appcompat.R.id.search_src_text) as SearchView.SearchAutoComplete

        mSearchAutoComplete.setDropDownBackgroundResource(R.drawable.abc_action_bar_item_background_material);
        mSearchAutoComplete.setDropDownAnchor(R.id.action_search);
        mSearchAutoComplete.setThreshold(0)
        val countries = arrayOf<String>("India","USA","China","Australia","Sri Lanka") //an array of Strings

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, countries)
        mSearchAutoComplete.setAdapter(adapter);
*/

/*
        sv = MenuItemCompat.getActionView(item)
        sv=menui
        sa = sv.findViewById(android.support.v7.appcompat.R.id.search_src_text)
*/
        return true
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_organization -> {
                // Handle the camera action
            }
            R.id.nav_users -> {

            }
            R.id.nav_department -> {
                val builder = AlertDialog.Builder(this@superadmin_homepage)

                builder.setTitle("Add department")
                val view: View = layoutInflater.inflate(
                    R.layout.department_dialoague, // Custom view/ layout
                    null, // Root layout to attach the view
                    false // Attach with root layout or not
                )
                builder.setView(view)
                builder.setPositiveButton("ADD ") { dialog, which ->

                    var adddepartmentEditText:EditText =  view.findViewById(R.id.et_department_superadmin_homepage);

                    var adddepartmentstring = adddepartmentEditText.text.toString()
                    Log.e("error","==="+adddepartmentstring)

                    var mDatabase2: DatabaseReference? = null
                    mDatabase2 =
                            mDatabase!!.reference!!.child("organizations/" + mAuth!!.uid + "/department/"+adddepartmentstring)
                    mDatabase2!!.child("departmentid").setValue("1")


                    Toast.makeText(this,"Toast Add",Toast.LENGTH_LONG).show()
                }
                builder.setNeutralButton("Cancel") { _, _ ->
                }

                val dialog: AlertDialog = builder.create()
                dialog.show()

            }
            R.id.nav_category-> {
             //   departmentmodel!!.clear();
                 var mDatabaseReferencedepartment: DatabaseReference?= mDatabase!!.reference!!.child("organizations/"+mAuth!!.uid+"/department")


                mDatabaseReferencedepartment!!.addValueEventListener(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {

                    }
                    override fun onDataChange(p0: DataSnapshot) {
                            //Log.d("firebasedata","=="+p0.value)
                        if(p0!!.exists())
                        {
                            for (i in p0.children){
                                var obj=     departmentmodel()
                                obj.departmentid=""+i.child("departmentid").value
                                obj.departmentname=""+i.key

                                departmentmodel!!.add(obj)
                                Log.d("firebase1key","====="+i.key)
                                Log.d("firebase1child","====="+i.child("departmentid").value)
                            }
                            Log.d("firebase1child","====="+departmentmodel!!.size)
                            //Log.d("arraaylistfi","====="+departmentmodel!!.size)

                        }
                    }
                });

                Log.d("arraaylistf","====="+departmentmodel!!.size)
                var arrayList: ArrayList<String>? = ArrayList<String>()
                arrayList!!.clear();
                for(i in 0 until departmentmodel!!.size) {
                    arrayList!!.add(departmentmodel!!.get(i).departmentname)
                    Log.d("arraaylist", "==="+arrayList!!.get(i))

                }

                val builder = AlertDialog.Builder(this@superadmin_homepage)

                builder.setTitle("Add category")
                val view: View = layoutInflater.inflate(
                    R.layout.addcategory, // Custom view/ layout
                    null, // Root layout to attach the view
                    false // Attach with root layout or not
                )
                var addcategoryEditText:EditText =  view.findViewById(R.id.et_category_superadmin_homepage);
                var categoryspinner :Spinner = view!!.findViewById(R.id.sp_addcategory_superadmin_homepage)

                Log.d("arraaylist", "==="+arrayList!!.size)
                val array_adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayList)
                array_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                categoryspinner.adapter=array_adapter

                builder.setView(view)
                builder.setPositiveButton("ADD ") { dialog, which ->


                    Toast.makeText(this,"Toast Add",Toast.LENGTH_LONG).show()
                }
                builder.setNeutralButton("Cancel") { _, _ ->
                }

                val dialog: AlertDialog = builder.create()
                dialog.show()



            }
            R.id.nav_aboutus -> {

            }
            R.id.nav_setting-> {

            }
            R.id.nav_rateus->{

            }
            R.id.nav_logout->{

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}


private fun Any?.LayoutParams(right: Int): ViewGroup.LayoutParams? {

    return null;
}




