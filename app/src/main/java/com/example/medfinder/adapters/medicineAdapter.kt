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
import com.example.medfinder.medicine
import com.google.firebase.storage.FirebaseStorage
import java.io.File


class medicineAdapter  (val context: Context,val userList: ArrayList<medicine>) :RecyclerView.Adapter<medicineAdapter.ViewHolder>()  {

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){


        val dragName=itemView.findViewById<TextView>(R.id.edtdrgnme)
        val dragdiscription=itemView.findViewById<TextView>(R.id.edtdrgDesc)
        val img= itemView.findViewById<ImageView>(R.id.adImage)
        val editdata=itemView.findViewById<Button>(R.id.editdrugrequest)
        val delete=itemView.findViewById<Button>(R.id.delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View= LayoutInflater.from(context).inflate(R.layout.medicinlayout,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentUser=userList[position]

        holder.dragName.text=currentUser.name
        holder.dragdiscription.text=currentUser.Description

        val strogeRef = FirebaseStorage.getInstance().reference.child(currentUser.img.toString())

        val localfile= File.createTempFile("tempImage","jpg")
        strogeRef.getFile(localfile).addOnSuccessListener {

            val bitmap= BitmapFactory.decodeFile(localfile.absolutePath)
            holder.img.setImageBitmap(bitmap)

        }.addOnFailureListener{

        }

        holder.editdata.setOnClickListener {
//
            val intent = Intent(context, popup::class.java)
//
            intent.putExtra("name",currentUser.name)
            intent.putExtra("dis",currentUser.Description)
            intent.putExtra("uid",currentUser.uid)
            intent.putExtra("img",currentUser.img)

            context.startActivity(intent)

        }

        holder.delete.setOnClickListener {
            val intent = Intent(context, deletemed::class.java)
//
            intent.putExtra("name",currentUser.name)
            intent.putExtra("dis",currentUser.Description)
            intent.putExtra("uid",currentUser.uid)

            context.startActivity(intent)
        }
    }

}