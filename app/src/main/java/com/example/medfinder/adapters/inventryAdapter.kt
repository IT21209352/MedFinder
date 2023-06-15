package com.example.medfinder.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.medfinder.*
import com.example.medfinder.inventry

class inventryAdapter (val context: Context, val userList: ArrayList<inventry>) :
    RecyclerView.Adapter<inventryAdapter.UserViewHolder>() {

    class UserViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val textName=itemView.findViewById<TextView>(R.id.text_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view: View= LayoutInflater.from(context).inflate(R.layout.user_layout,parent,false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentUser=userList[position]

        holder.textName.text=currentUser.name

        holder.itemView.setOnClickListener{
            val intent= Intent(context, DrugInventory::class.java)

            intent.putExtra("name",currentUser.name)
            intent.putExtra("dosage",currentUser.Dosage)
            intent.putExtra("qty",currentUser.qty)
            intent.putExtra("price",currentUser.price)
            intent.putExtra("exp",currentUser.exp)
            intent.putExtra("i_id",currentUser.i_id)

            context.startActivity(intent)
        }
    }
}