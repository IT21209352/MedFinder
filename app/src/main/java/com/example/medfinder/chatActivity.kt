package com.example.medfinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.medfinder.adapters.MassageAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class chatActivity : AppCompatActivity() {

    private lateinit var  chatRecyclerView:RecyclerView
    private lateinit var massageBox:EditText
    private lateinit var sendButton:ImageView
    private lateinit var  massageAdapter: MassageAdapter
    private lateinit var massageList:ArrayList<Massage>
    private lateinit var mDbref:DatabaseReference


    var receiverRoom:String?=null
    var senderRoom:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)



        val name=intent.getStringExtra("name")
        val receiverUid=intent.getStringExtra("uid")
        val senderUid=FirebaseAuth.getInstance().currentUser?.uid

        mDbref= FirebaseDatabase.getInstance().getReference()

        senderRoom=receiverUid + senderUid
        receiverRoom=senderUid+receiverUid


        supportActionBar?.title=name

        chatRecyclerView=findViewById(R.id.chatRecyclerView)
        massageBox=findViewById(R.id.massageBox)
        sendButton=findViewById(R.id.sendbutton)
        massageList=ArrayList()
        massageAdapter= MassageAdapter(this, massageList)


        chatRecyclerView.layoutManager=LinearLayoutManager(this)
        chatRecyclerView.adapter=massageAdapter


        mDbref.child("chats").child(senderRoom!!).child("massage")
            .addValueEventListener(object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {

                    massageList.clear()
                    for(postSnapshot in snapshot.children){

                        val message=postSnapshot.getValue(Massage::class.java)
                        massageList.add(message!!)

                    }
                    massageAdapter.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })



        sendButton.setOnClickListener{

            val massage=massageBox.text.toString()

            val massageobject= Massage(massage,senderUid)

            mDbref.child("chats").child(senderRoom!!).child("massage").push()
                .setValue(massageobject).addOnSuccessListener {

                    mDbref.child("chats").child(receiverRoom!!).child("massage").push()
                        .setValue(massageobject)
                }
            massageBox.setText("")
        }
    }
}