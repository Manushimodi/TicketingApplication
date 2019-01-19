package com.example.nisargdoshi.ticktingapplication

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        val sharedPreference =  getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        var shareduser=sharedPreference.getString("username","")
        var sharedpwd=sharedPreference.getString("password","")
        Log.d("shareddata","==="+sharedpwd+"==="+shareduser);
        if(!shareduser.equals("") || !sharedpwd.equals("")){
            val intent = Intent(this, superadmin_homepage::class.java)
            startActivity(intent)
        }else{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}
