package com.example.medfinder

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import com.example.medfinder.adapters.pharmacy
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class pha_myaccount : AppCompatActivity() {

    lateinit var bckphahome:ImageButton ;

    private lateinit var getname: EditText
    private lateinit var getemail: EditText
    private lateinit var getphone: EditText
    private lateinit var getaddress: EditText
    private lateinit var  auth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference
    private lateinit var reg:String

    private  lateinit var edit: Button


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pha_myaccount)
        getSupportActionBar()?.hide()
        bckphahome = findViewById(R.id.bck_btn_prof_to_pha_home)
        getname =findViewById(R.id.edtprofPhaName)
        getemail=findViewById(R.id.pha_prof_edtEmail)
        getphone=findViewById(R.id.pha_prof_edt_PhoneNo)
        getaddress=findViewById(R.id.pha_prof_edtAddress)
        auth= FirebaseAuth.getInstance()
        mDbRef= FirebaseDatabase.getInstance().getReference()
        edit=findViewById(R.id.my_Acc_Edit)


        mDbRef.child("user").child(auth.currentUser?.uid.toString()).addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                val currentUser=snapshot.getValue(pharmacy::class.java)
                println(currentUser?.name)
                println(currentUser?.email)
                println(currentUser?.uid)
                println(currentUser?.phone)

                getname.setText(currentUser?.name)
                getphone.setText(currentUser?.phone)
                getaddress.setText(currentUser?.address)
                getemail.setText(currentUser?.email)
                reg =currentUser?.reg_no!!



            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        edit.setOnClickListener {

            var name= getname.text.toString()
            var phone= getphone.text.toString()
            var address= getaddress.text.toString()
            var email= getemail.text.toString()

            mDbRef.child("user").child(auth.currentUser?.uid.toString()).setValue(pharmacy(name,email,auth.currentUser?.uid.toString(),phone,address,"phamacy",reg,true))
            val intent = Intent(this, pharmacy_home::class.java)
//



            startActivity(intent)
        }

    }

    fun bckTophaHome(view: View){
        val p_home = Intent(this,pharmacy_home::class.java)
        startActivity(p_home)
        finish()
    }

    fun btnDeleteAcc(view: View){
        val delacc = Intent(this,DeleteAccount::class.java)
        startActivity(delacc)
        finish()
    }
}