package com.example.nisargdoshi.ticktingapplication


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_fragment_user_detail.*
import kotlinx.android.synthetic.main.fragment_fragment_user_detail.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


/**
 * A simple [Fragment] subclass.
 *
 */
class FragmentUserDetail : Fragment() {

    var emailpattern = ("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
            + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
            + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
            + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
            + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
            + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").toRegex()


    var  passwordpattern= "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})".toRegex()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_user_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        view.btn_usersubmit_registeractivity.setOnClickListener() {
            Log.d("firstnameee","===="+et_userfirstname_registeractivity.text.toString());
           val firstname=et_userfirstname_registeractivity.text.toString()
           val lastname=et_userlastname_registeractivity.text.toString()
           val email=et_useremail_registeractivity.text.toString()
           val pass= et_userpassword_registeractivity.text.toString()
            if(firstname.toString().equals("") || firstname.toString()==null) {
                Toast.makeText(activity,"Please enter first name",Toast.LENGTH_LONG).show()
            }
            else if(lastname.toString().equals("") || lastname.toString()==null) {
            Toast.makeText(activity,"Please enter last name",Toast.LENGTH_LONG).show()
            }
           else if (email.equals("") || email.toString() == null) {
                Toast.makeText(activity,"Please enter email",Toast.LENGTH_LONG).show()
            }
            else if (pass.equals("") || pass.toString() == null) {
                Toast.makeText(activity,"Please enter passsword",Toast.LENGTH_LONG).show()
            }
            else if (!et_useremail_registeractivity.text.matches(emailpattern)) {
                Toast.makeText(activity,"Invalid email id",Toast.LENGTH_LONG).show()
            }

            else if (!et_userpassword_registeractivity.text.matches(passwordpattern)) {
                Toast.makeText(activity,"Password must contain 6 characters or more with at least one uppercase letter, one special character, one number and one letter.",Toast.LENGTH_LONG).show()
            }
            else {
                val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
                val args = Bundle()
                args.putString("firstname", et_userfirstname_registeractivity.text.toString())
                args.putString("lastname", et_userlastname_registeractivity.text.toString())
                args.putString("email", et_useremail_registeractivity.text.toString())
                args.putString("password", et_userpassword_registeractivity.text.toString())
                var fragmentOrganizatoinDetail = FragmentOrganizatoinDetail()
                fragmentOrganizatoinDetail.arguments = args
                fragmentTransaction.replace(R.id.ll_registercontainer_registeractivity, fragmentOrganizatoinDetail)
                fragmentTransaction.addToBackStack("userfragment");
                fragmentTransaction.commit()
            }
        }
    }

}
