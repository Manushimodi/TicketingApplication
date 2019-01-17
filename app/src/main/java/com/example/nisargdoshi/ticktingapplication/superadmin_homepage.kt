package com.example.nisargdoshi.ticktingapplication

import android.app.ActionBar
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat

import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_superadmin_homepage.*
import kotlinx.android.synthetic.main.app_bar_superadmin_homepage.*
import android.view.Gravity
import android.view.ViewGroup


class superadmin_homepage : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_superadmin_homepage)
        setSupportActionBar(toolbar)
        setTitle("Home")

        actionBar?.setTitle("Hohgvgh")
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

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



    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_organization -> {
                // Handle the camera action
            }
            R.id.nav_users -> {

            }
            R.id.nav_department -> {

            }
            R.id.nav_category-> {

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




