package com.example.nisargdoshi.ticktingapplication


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_fragment_organizatoin_detail.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class FragmentOrganizatoinDetail : Fragment() {


    private var mAuth: FirebaseAuth? = null
    private var mDatabaseReference: DatabaseReference? = null
    private var mDatabase: FirebaseDatabase? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_organizatoin_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mDatabase = FirebaseDatabase.getInstance()
        mDatabaseReference = mDatabase!!.reference!!.child("organizations")
        mAuth = FirebaseAuth.getInstance()


        val firstname = arguments!!.getString("firstname")
        val lastname = arguments!!.getString("lastname")
        val email = arguments!!.getString("email")
        val password = arguments!!.getString("password")

        btn_organizationregister_registeractivity.setOnClickListener(){
            val oname = et_organizationname_registeractivity.text.toString()
            val owebsite = et_organizationwebsite_registeractivity.text.toString()
            val oemail = et_organizationemail_registeractivity.text.toString()
            val ocontact = et_organizationcontact_registeractivity.text.toString()

            if(oname.toString().equals("") || oname.toString()==null){
                Toast.makeText(activity,"Please enter name",Toast.LENGTH_LONG).show()
            }
            else if(owebsite.toString().equals("") || owebsite.toString()==null){
                Toast.makeText(activity,"Please enter website",Toast.LENGTH_LONG).show()
            }

            else if(oemail.toString().equals("") || oemail.toString()==null){
                Toast.makeText(activity,"Please enter email",Toast.LENGTH_LONG).show()
            }

            else if(ocontact.toString().equals("") || ocontact.toString()==null){
                Toast.makeText(activity,"Please enter contact number",Toast.LENGTH_LONG).show()
            }

            else {
                mAuth!!.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(RegisterActivity()) { task ->
                        if (task.isSuccessful) {
                            val user=mAuth!!.currentUser

                            user!!.sendEmailVerification()
                                .addOnCompleteListener(RegisterActivity()){task ->
                                    if(task.isSuccessful){
                                        var mDatabase1: DatabaseReference? = null
                                        var mDatabase2: DatabaseReference? = null

                                        mDatabase1 =
                                                mDatabase!!.reference!!.child("organizations/" + mAuth!!.uid + "/organizationdetails")
                                        mDatabase2 =
                                                mDatabase!!.reference!!.child("organizations/" + mAuth!!.uid + "/userdetails/superadmin/" + mAuth!!.uid)


                                        mDatabase1!!.child("organizationname").setValue(oname);
                                        mDatabase1!!.child("organizationwebsite").setValue(owebsite)
                                        mDatabase1!!.child("organizationemailid").setValue(oemail)
                                        mDatabase1!!.child("organizationcontactnumber").setValue(ocontact)
                                        mDatabase2!!.child("firstname").setValue(firstname)
                                        mDatabase2!!.child("lastname").setValue(lastname)

                                        Toast.makeText(context,"Email verification mail sent",Toast.LENGTH_LONG).show()
                                    }
                                    else{
                                        Toast.makeText(context,"Something went wrong in sending verification email",Toast.LENGTH_LONG).show()
                                        Log.d("Registrationmessage", "fail")
                                    }
                                }
                        } else {
                            Toast.makeText(context,"Something went wrong in creating user",Toast.LENGTH_LONG).show()
                            Log.d("Registrationmessage", "fail")
                        }
                    }
                }
            }
    }
}


