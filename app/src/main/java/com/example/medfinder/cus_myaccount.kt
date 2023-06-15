package com.example.medfinder

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class cus_myaccount : AppCompatActivity() {

    lateinit var bkhome : ImageButton
    lateinit var edtdetils : Button
    lateinit var delaccount : Button


    private lateinit var getname: EditText
    private lateinit var getemail: EditText
    private lateinit var getphone: EditText
    private lateinit var getaddress: EditText


    private lateinit var  userList: ArrayList<User>

    private lateinit var  auth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference

    private  lateinit var edit:Button


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cus_myaccount)

        bkhome = findViewById(R.id.bck_btn_home)
        delaccount = findViewById(R.id.my_Acc_Delete)


        edit=findViewById(R.id.my_Acc_Edit)
        getname=findViewById(R.id.my_acc_edt_fname)
        getemail=findViewById(R.id.my_acc_edt_email)
        getphone=findViewById(R.id.my_acc_edt_phone)
        getaddress=findViewById(R.id.my_acc_edt_Address)

        auth= FirebaseAuth.getInstance()
        mDbRef= FirebaseDatabase.getInstance().getReference()
        userList =ArrayList()


        mDbRef.child("user").child(auth.currentUser?.uid.toString()).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                val currentUser=snapshot.getValue(User::class.java)
                println(currentUser?.name)
                println(currentUser?.email)
                println(currentUser?.uid)
                println(currentUser?.phone)

                getname.setText(currentUser?.name)
                getphone.setText(currentUser?.phone)
                getaddress.setText(currentUser?.address)
                getemail.setText(currentUser?.email)


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

            mDbRef.child("user").child(auth.currentUser?.uid.toString()).setValue(User(name,email,auth.currentUser?.uid.toString(),phone,address,"user"))

        }


    }

    fun bckTohome(view: View){
        val bckhome = Intent(this,home::class.java)
        startActivity(bckhome)
        finish()
    }

    fun btnDeleteAcc(view: View){
        val delacc = Intent(this,DeleteAccount::class.java)
        startActivity(delacc)
        finish()
    }

//    fun ChngPWacc(view: View){
//        val changepwacc = Intent(this,change_pw::class.java)
//        startActivity(changepwacc)
//        finish()
//    }
}