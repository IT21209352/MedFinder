package com.example.medfinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

private lateinit var name: TextView
private lateinit var dis: TextView
private lateinit var cancel: Button
private lateinit var delete: Button
private lateinit var auth: FirebaseAuth
private lateinit var mDbRef: DatabaseReference

class deletemed : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deletemed)

        getSupportActionBar()?.hide()

        name=findViewById(R.id.drgnme)
        dis=findViewById(R.id.drgDesc)
        cancel=findViewById(R.id.deletecancle)
        delete=findViewById(R.id.delete)
        auth= FirebaseAuth.getInstance()
        mDbRef= FirebaseDatabase.getInstance().getReference()



        name.setText(intent.getStringExtra("name"))
        dis.setText(intent.getStringExtra("dis"))

        cancel.setOnClickListener {
            val intent= Intent(this, home::class.java)


            this.startActivity(intent)
        }

        delete.setOnClickListener {



           deleteUserToDatabase(auth.currentUser?.uid!!)

        }


    }
    private fun deleteUserToDatabase(uid:String){


        mDbRef= FirebaseDatabase.getInstance().getReference()


        mDbRef.child("medicine").child(uid).child(intent.getStringExtra("uid")!!).removeValue()

        val intent= Intent(this, home::class.java)
        this.startActivity(intent)


    }
}