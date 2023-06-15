package com.example.medfinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class signups : AppCompatActivity() {

    lateinit var sign_pha:Button
    lateinit var sign_cus:Button
    lateinit var bck_to_log:Button
    private lateinit var auth: FirebaseAuth

    private lateinit var getname: EditText
    private lateinit var getemail: EditText
    private lateinit var getpasssword: EditText
    private lateinit var getphone:EditText
    private  lateinit var  getaddress:EditText

    private lateinit var mDbref:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signups)
        getSupportActionBar()?.hide()
        auth= FirebaseAuth.getInstance()

        getname=findViewById(R.id.name)
        getemail = findViewById(R.id.Email)
        getpasssword = findViewById(R.id.Password)
        getphone=findViewById(R.id.cus_reg_edtPhoneNo)
        getaddress=findViewById(R.id.cus_reg_edtAddress)


        sign_pha = findViewById(R.id.cus_reg_as_pha)
        sign_cus = findViewById(R.id.cus_signup)
        bck_to_log = findViewById(R.id.back_log)

    }

    fun signupPHA(view: View){
        val signup_to_phamacy = Intent(this,phamacy_register::class.java)
        startActivity(signup_to_phamacy)
        finish()
    }

    fun  signupCus(view: View){



        val name=getname.text.toString()
        val password=getpasssword.text.toString()
        val email=getemail.text.toString()
        val address=getaddress.text.toString()
        val phone=getphone.text.toString()

        signupdata(email, password ,name,address,phone)

    }

    fun backtologin(view:View){
        val bk_log_btn = Intent(this,Login::class.java)
        startActivity(bk_log_btn)
        finish()
    }


    private fun signupdata(email:String,password:String,name:String,address:String,phone:String){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    addUserToDatabase(name,email,address,phone,auth.currentUser?.uid!!)
                    val signup_to_cus = Intent(this,home::class.java)
                    startActivity(signup_to_cus)
                } else {
                    Toast.makeText(this,"some error" , Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun addUserToDatabase(name:String,email:String,address:String,phone:String,uid:String){

        mDbref=FirebaseDatabase.getInstance().getReference()

        mDbref.child("user").child(uid).setValue(User(name,email,uid,phone,address,"user"))


    }

}