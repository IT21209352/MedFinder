package com.example.medfinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.medfinder.adapters.inventryAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*




class findinventry : AppCompatActivity() {

    lateinit var userRecyclerView: RecyclerView
    private lateinit var  userList: ArrayList<inventry>
    private lateinit var adapter: inventryAdapter
    private lateinit var  auth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_findinventry)

//        val context: Context = requireContext()
        auth= FirebaseAuth.getInstance()
        mDbRef= FirebaseDatabase.getInstance().getReference()

        userList =ArrayList()
        adapter= inventryAdapter(this,userList )

        userRecyclerView=findViewById(R.id.userRecyclerView)

        val layoutManager = LinearLayoutManager(this)
        userRecyclerView.layoutManager = layoutManager

        userRecyclerView.adapter=adapter

        mDbRef.child("inventory").child(auth.currentUser?.uid!!).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                userList.clear()
                for(postSnapshot in snapshot.children){
                    val currentUser=postSnapshot.getValue(inventry::class.java)

                        userList.add(currentUser!!)


                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })


    }
}