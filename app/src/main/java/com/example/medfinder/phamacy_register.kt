package com.example.medfinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.example.medfinder.adapters.pharmacy
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class phamacy_register : AppCompatActivity() {

    lateinit var bck_cus_reg:ImageButton
    lateinit var phamacy_home:Button
    private lateinit var getname: EditText
    private lateinit var getemail: EditText
    private lateinit var getpasssword: EditText
    private lateinit var regNo: EditText
    private lateinit var getphone: EditText
    private  lateinit var  getaddress: EditText

    private lateinit var auth: FirebaseAuth
    private lateinit var mDbref: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phamacy_register)

        getSupportActionBar()?.hide()

        auth= FirebaseAuth.getInstance()
        mDbref= FirebaseDatabase.getInstance().getReference()

        bck_cus_reg= findViewById(R.id.bck_btn_to_cus)
        phamacy_home=findViewById(R.id.pha_signup)

        getname=findViewById(R.id.edtPhaName)
        regNo = findViewById(R.id.edtregNo)
        getemail = findViewById(R.id.pha_reg_edtEmail)
        getpasssword = findViewById(R.id.pha_reg_pw)
        getphone=findViewById(R.id.pha_reg_edtPhoneNo)
        getaddress=findViewById(R.id.pha_reg_edtAddress)
    }

    fun bckToCusReg(view: View){
        val bk_Btn_Cus_reg = Intent(this,signups::class.java)
        startActivity(bk_Btn_Cus_reg)
        finish()
    }

    fun phamcy_sign(view: View){


        val name=getname.text.toString()
        val password=getpasssword.text.toString()
        val email=getemail.text.toString()
        val address=getaddress.text.toString()
        val phone=getphone.text.toString()
        val regno=regNo.text.toString()

        signupdata(email, password ,name,address,phone,regno)


    }


    private fun signupdata(email:String,password:String,name:String,address:String,phone:String,regno:String){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    addUserToDatabase(name,email,address,phone,auth.currentUser?.uid!!,regno)
                    val phamacy_signin = Intent(this,Login::class.java)
                    startActivity(phamacy_signin)
                } else {
                    Toast.makeText(this,"Wait for approvel " , Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun addUserToDatabase(name:String,email:String,address:String,phone:String,uid:String,regno:String){

        mDbref= FirebaseDatabase.getInstance().getReference()

        mDbref.child("user").child(uid).setValue(pharmacy(name,email,uid,phone,address,"phamacy",regno,false))


    }
}