package com.example.nisargdoshi.ticktingapplication

import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_forgot.*

class ForgotActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null
    private var mProgressBar: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot)

        mAuth = FirebaseAuth.getInstance()
        mProgressBar = ProgressDialog(this,R.style.Base_Theme_AppCompat_Light_Dialog)

        btn_reset_forgotactivity.setOnClickListener(){
            val resetemail = et_resetemail_forgotactivity.text.toString()

            mAuth!!.sendPasswordResetEmail(resetemail)
                .addOnCompleteListener(this) {task ->
                    if(task.isSuccessful){
                            Toast.makeText(this,"check email for verification", Toast.LENGTH_LONG).show()
                    }
                    else{

                        Toast.makeText(this,"fail to reset password"+task.exception, Toast.LENGTH_LONG).show()
                    }
                }
        }



    }
}

