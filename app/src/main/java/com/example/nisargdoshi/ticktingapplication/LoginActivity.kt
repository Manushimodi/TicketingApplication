package com.example.nisargdoshi.ticktingapplication

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private var mAuth: FirebaseAuth? = null
    private var mDatabaseReference: DatabaseReference? = null
    private var mDatabase: FirebaseDatabase? = null
    private var mProgressBar: ProgressDialog? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setTitle("Login")
        mAuth = FirebaseAuth.getInstance()


        mProgressBar = ProgressDialog(this,R.style.Base_Theme_AppCompat_Light_Dialog)

        tv_registernow_loginactivity.setOnClickListener(){
            val intent = Intent(this, RegisterActivity::class.java);
            startActivity(intent)
        }
        tv_forgotpassword_loginactivity.setOnClickListener(){
            val intent = Intent(this, ForgotActivity::class.java);
            startActivity(intent)
        }

        btn_loginsubmit_loginactivity.setOnClickListener() {
            val email = et_loginemail_loginactivity.text.toString()
            val pwd = et_loginpassword_loginactivity.text.toString()
            if(email.toString().equals("") || email.toString()==null) {
                Toast.makeText(applicationContext, "please enter email", Toast.LENGTH_LONG).show()
            }
            else if(pwd.toString().equals("") || pwd.toString()==null) {
                Toast.makeText(applicationContext, "please enter password", Toast.LENGTH_LONG).show()
            }
            else{
                mProgressBar!!.show();
                mProgressBar!!.setMessage("Login..")


                mAuth!!.signInWithEmailAndPassword(email, pwd)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        mProgressBar!!.dismiss()
                        Toast.makeText(applicationContext, "login successfull", Toast.LENGTH_SHORT).show()
                    } else {
                        mProgressBar!!.dismiss()
                        Toast.makeText(applicationContext, "Authentication failed!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
