package com.example.medfinder.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.medfinder.Massage
import com.example.medfinder.R
import com.google.firebase.auth.FirebaseAuth

class MassageAdapter(val context:Context, val massageList:ArrayList<Massage>):
    RecyclerView .Adapter<RecyclerView.ViewHolder>(){

    val ITEM_RECEIVE=1
    val ITEM_SENT=2

    class SentViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val sentMassage=itemView.findViewById<TextView>(R.id.text_sent_massage)
    }
    class ReceiverViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val receiverMassage=itemView.findViewById<TextView>(R.id.text_receiver_massage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType==1){
            val view: View= LayoutInflater.from(context).inflate(R.layout.receive,parent,false)
            return ReceiverViewHolder(view)
        }else{

            val view: View= LayoutInflater.from(context).inflate(R.layout.sendmassage,parent,false)
            return SentViewHolder(view)
        }

    }

    override fun getItemCount(): Int {
        return massageList.size
    }

    override fun getItemViewType(position: Int): Int {

        val currentMassage=massageList[position]
        if (FirebaseAuth.getInstance().currentUser?.uid.equals(currentMassage.senderId)){
            return ITEM_SENT
        }else{
            return ITEM_RECEIVE
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val currentMassage=massageList[position]

        if(holder.javaClass== SentViewHolder::class.java){

            val viewHolder=holder as SentViewHolder
            holder.sentMassage.text=currentMassage.massage

        }else{

            val viewHolder=holder as ReceiverViewHolder
            holder.receiverMassage.text=currentMassage.massage
        }

    }
}