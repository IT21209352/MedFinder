package com.example.medfinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.medfinder.adapters.advertismentAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class Advertisments : AppCompatActivity() {

    lateinit var add_adv:Button
    lateinit var approvel:Button
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var  userList: ArrayList<advertisment>
    private lateinit var adapter: advertismentAdapter
    private lateinit var  auth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference

    private lateinit var img :ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_advertisments)
        getSupportActionBar()?.hide()
        approvel=findViewById(R.id.approvel)
        approvel.setOnClickListener {
            val intent= Intent(this, approve::class.java)



            startActivity(intent)
        }

        add_adv = findViewById(R.id.adv_add_page)

        auth= FirebaseAuth.getInstance()
        mDbRef= FirebaseDatabase.getInstance().getReference()

        userList =ArrayList()
        adapter= advertismentAdapter(this,userList )

        userRecyclerView=findViewById(R.id.addrecyclerView)

        val layoutManager = LinearLayoutManager(this)
        userRecyclerView.layoutManager = layoutManager

        userRecyclerView.adapter=adapter

        mDbRef.child("image").addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                userList.clear()
                for(postSnapshot in snapshot.children){
                    val currentUser=postSnapshot.getValue(advertisment::class.java)

                    userList.add(currentUser!!)


                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })




    }

    fun toaddadvpge(view: View){
        val add_adv_pge = Intent(this,Advertisment_add::class.java)
        startActivity(add_adv_pge)
        finish()
    }

}