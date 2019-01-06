package com.example.nisargdoshi.ticktingapplication

import android.app.Activity
import android.app.PendingIntent.getActivity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.LinearLayout

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val LinearLayout = findViewById(R.id.ll_registercontainer_registeractivity) as LinearLayout

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.ll_registercontainer_registeractivity, FragmentUserDetail())
        // fragmentTransaction.addToBackStack(null).commit();
        fragmentTransaction.commit()
            //  activityded=getac;

    }
}
