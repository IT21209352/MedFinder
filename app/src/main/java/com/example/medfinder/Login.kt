package com.example.medfinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.medfinder.adapters.pharmacy
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class Login : AppCompatActivity() {

    lateinit var signup: Button
    lateinit var log:Button
    private lateinit var mAuth: FirebaseAuth

    private lateinit var getemail: EditText
    private lateinit var getpasssword: EditText
    private lateinit var mDbref: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        getSupportActionBar()?.hide()
        mAuth= FirebaseAuth.getInstance()
        getemail = findViewById(R.id.email)
        getpasssword = findViewById(R.id.password1)
        signup = findViewById(R.id.sign_up_btn)
        log = findViewById(R.id.log_btn)
    }

    fun signup_to_acc(view: View){
        val signuptoall = Intent(this,signups::class.java)
        startActivity(signuptoall)
        finish()
    }

    fun Login_to_acc(view: View){
        val email=getemail.text.toString()
        val password=getpasssword.text.toString()
        logindata(email,password)
    }


    private fun logindata(email:String,password:String){


        if(email=="" || password==""){
            Toast.makeText(this, "fill all filds", Toast.LENGTH_SHORT).show()
        }else if (email=="admin@gmail.com" && password=="123456"){
            val lgn = Intent(this,Advertisments::class.java)
            startActivity(lgn)
        }else {

            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {

                        val lgn = Intent(this, home::class.java)
                        val phamacy_signin = Intent(this,pharmacy_home::class.java)

                        mDbref= FirebaseDatabase.getInstance().getReference()

                        mDbref.child("user").child(mAuth.currentUser?.uid!!)
                            .addValueEventListener(object : ValueEventListener {
                                override fun onDataChange(snapshot: DataSnapshot) {




                                    val user=snapshot.getValue(pharmacy::class.java)

                                    if (user?.type=="user"){


                                        startActivity(lgn)
                                    }else if(user?.type=="phamacy" && user?.approve== true){

                                        startActivity(phamacy_signin)
                                    }




                                }

                                override fun onCancelled(error: DatabaseError) {
                                    TODO("Not yet implemented")
                                }

                            })

                    }
                }
        }
    }
}