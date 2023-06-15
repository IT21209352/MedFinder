package com.example.medfinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton

class change_pw : AppCompatActivity() {

    lateinit var chngepwbackToaccount : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_pw)

        chngepwbackToaccount = findViewById(R.id.changepw_myacc_bck_btn)
    }

    fun pwbckmyaccount(view: View){
        val bcktomyacc = Intent(this,cus_myaccount::class.java)
        startActivity(bcktomyacc)
        finish()

    }
}