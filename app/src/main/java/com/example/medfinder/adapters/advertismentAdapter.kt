package com.example.medfinder.adapters

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.medfinder.*
import com.example.medfinder.advertisment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.io.File

class advertismentAdapter(val context: Context, val userList: ArrayList<advertisment>) :
    RecyclerView.Adapter<advertismentAdapter.UserViewHolder>() {

    private lateinit var mDbRef: DatabaseReference

    class UserViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val discription =itemView.findViewById<TextView>(R.id.dis)
        val img =itemView.findViewById<ImageView>(R.id.adv_img_btn)
        val edit=itemView.findViewById<Button>(R.id.edt_adv_btn)
        val delete =itemView.findViewById<Button>(R.id.dlt_adv_btn)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder{
        val view:View = LayoutInflater.from(context).inflate(R.layout.advertismentlist,parent,false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentUser=userList[position]

        val strogeRef = FirebaseStorage.getInstance().reference.child(currentUser.i_id.toString())

        val localfile= File.createTempFile("tempImage","jpg")
        strogeRef.getFile(localfile).addOnSuccessListener {

            val bitmap= BitmapFactory.decodeFile(localfile.absolutePath)
            holder.img.setImageBitmap(bitmap)
            holder.discription.setText(currentUser.description)

        }.addOnFailureListener{

        }


        holder.delete.setOnClickListener {
            mDbRef= FirebaseDatabase.getInstance().getReference()
            mDbRef.child("image").child(currentUser.i_id.toString()).removeValue()

        }
        holder.edit.setOnClickListener {

            val intent = Intent(context, Advertisment_add::class.java)
//
            intent.putExtra("dis",currentUser.description)
            intent.putExtra("id",currentUser.i_id)


            context.startActivity(intent)
        }



    }



    override fun getItemCount(): Int {
       return userList.size
    }


}