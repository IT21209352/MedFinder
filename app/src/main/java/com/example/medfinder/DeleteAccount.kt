package com.example.medfinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DeleteAccount : AppCompatActivity() {

    lateinit var delbackToaccount : ImageButton

    private lateinit var  auth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_account)

        delbackToaccount = findViewById(R.id.del_myacc_bck_btn)

        auth= FirebaseAuth.getInstance()
        mDbRef= FirebaseDatabase.getInstance().getReference()
    }

    fun delbckmyaccount(view: View){


        val bcktomyacc = Intent(this,cus_myaccount::class.java)
        startActivity(bcktomyacc)
        finish()
    }
    fun delete(view: View){

        mDbRef.child("user").child(auth.currentUser?.uid.toString()).removeValue()
        val bcktomyacc = Intent(this,Login::class.java)
        startActivity(bcktomyacc)
        finish()
    }
}