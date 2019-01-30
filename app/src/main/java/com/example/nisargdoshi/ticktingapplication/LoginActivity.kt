package com.example.nisargdoshi.ticktingapplication

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ServerValue
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private var mAuth: FirebaseAuth? = null
    private var mDatabaseReference: DatabaseReference? = null
    private var mDatabase: FirebaseDatabase? = null
    private var mProgressBar: ProgressDialog? = null

    val RC_SIGN_IN: Int = 1
    lateinit var mGoogleSignInClient: GoogleSignInClient
    lateinit var mGoogleSignInOptions: GoogleSignInOptions



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setTitle("Login")


        mAuth = FirebaseAuth.getInstance()
        //configureGoogleSignIn()

        mGoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.web_client_id))
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this,mGoogleSignInOptions)




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
                        var user=mAuth!!.currentUser

                        if(user!!.isEmailVerified){

                            val sharedPreference =  getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
                            var editor = sharedPreference.edit()
                            editor.putString("username",email)
                            editor.putString("password",pwd)
                            editor.commit()

                            val intent = Intent(this, superadmin_homepage::class.java)
                            startActivity(intent)
                            Toast.makeText(applicationContext, "login successfull", Toast.LENGTH_SHORT).show()

                        }else {
                            val builder = AlertDialog.Builder(this@LoginActivity)
                            builder.setTitle("Email verification alert!")
                            builder.setMessage("Please verify your email to continue...")
                            builder.setPositiveButton("RESEND") { dialog, which ->
                                user.sendEmailVerification()
                                    .addOnCompleteListener(this) { task ->
                                        if (task.isSuccessful) {
                                            Toast.makeText(this, "Email verification mail sent", Toast.LENGTH_LONG)
                                                .show()
                                        } else {
                                            Toast.makeText(
                                                this,
                                                "Something went wrong in sending verification email",
                                                Toast.LENGTH_LONG
                                            ).show()
                                        }
                                    }
                                }
                                builder.setNeutralButton("Cancel") { _, _ ->
                                }
                                val dialog: AlertDialog = builder.create()
                                dialog.show()


                        }
                    } else {
                        mProgressBar!!.dismiss()
                        Toast.makeText(applicationContext, "Authentication failed!", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }
        btn_googlebutton.setOnClickListener {
            val signInIntent: Intent = mGoogleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account!!)
            } catch (e: ApiException) {
                Toast.makeText(this, "Google failed:("+e.stackTrace, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        mAuth!!.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {

                var intent = Intent(this, superadmin_homepage::class.java)
                startActivity(intent)

            } else {
                Toast.makeText(this, "Google sign in failed:(", Toast.LENGTH_LONG).show()
            }
        }
    }


    private fun configureGoogleSignIn() {

    }
}
