package com.example.nisargdoshi.ticktingapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_intent_result_demo.*

class IntentResultDemo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_result_demo)

        btn_login.setOnClickListener {
            var intent = Intent(this,SumActivity::class.java)
            intent.putExtra("number1" , editText.text.toString())
            intent.putExtra("number2", editText1.text.toString())
            startActivityForResult(intent,10)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode==12 && requestCode==10){
            var m = data!!.getIntExtra("result",0);
            tv_intent.setText("ans"+m)
        }
    }
}
