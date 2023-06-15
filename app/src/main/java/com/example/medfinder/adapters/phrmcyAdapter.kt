package com.example.medfinder.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.medfinder.*

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class phrmcyAdapter(val context: Context, val userList: ArrayList<pharmacy>) :
    RecyclerView.Adapter<phrmcyAdapter.UserViewHolder>() {

    private lateinit var  auth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference

    class UserViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val uid=itemView.findViewById<TextView>(R.id.pha_acc_list_id)
        val Name=itemView.findViewById<TextView>(R.id.pha_acc_list_name)
        val address=itemView.findViewById<TextView>(R.id.pha_acc_list_address)

        val accept= itemView.findViewById<ImageButton>(R.id.add_pha_acc)
        val cancel=itemView.findViewById<ImageButton>(R.id.del_pha_accept)




    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view: View= LayoutInflater.from(context).inflate(R.layout.approvelist,parent,false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentUser=userList[position]
        holder.Name.text=currentUser.name
        holder.uid.text=currentUser.reg_no
        holder.address.text=currentUser.address
        mDbRef = FirebaseDatabase.getInstance().getReference()




        holder.accept.setOnClickListener {
            mDbRef.child("user").child(currentUser.uid!!).setValue(pharmacy(currentUser.name,currentUser.email,currentUser.uid.toString(),currentUser.phone,currentUser.address,currentUser.type,currentUser.reg_no.toString(),true))
            val intent = Intent(context, Advertisments::class.java)
//



            context.startActivity(intent)
        }

        holder.cancel.setOnClickListener {

            mDbRef.child("user").child(currentUser.uid!!).removeValue()

        }
    }

}