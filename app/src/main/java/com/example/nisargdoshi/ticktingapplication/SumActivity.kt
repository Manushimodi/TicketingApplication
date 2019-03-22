package com.example.nisargdoshi.ticktingapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_sum.*

class SumActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sum)

        val dbHandler = databasehandler(this, null, null, 1)

        dbHandler.addProduct()

/*
        var intent = intent
        var addition= Integer.parseInt(intent.getStringExtra("number1"))+ Integer.parseInt(intent.getStringExtra("number2"))
        intent.putExtra("result", addition)
        setResult(12, intent)
        finish()
*//*
        Glide.with(this)
            .load("https://upload.wikimedia.org/wikipedia/commons/thumb/e/e9/Felis_silvestris_silvestris_small_gradual_decrease_of_quality.png/200px-Felis_silvestris_silvestris_small_gradual_decrease_of_quality.png")
            .into(iv_thirdpartydemo)*/

    }
}
