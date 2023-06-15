package com.example.medfinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.medfinder.ui.notifications.NotificationsFragment

class pharmacy_home : AppCompatActivity() {

    lateinit var druginv:Button
    lateinit var drugaddinv:Button
    lateinit var phaprof:Button
    lateinit var drugreq:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pharmacy_home)

        druginv = findViewById(R.id.inventory)
        phaprof = findViewById(R.id.pha_account)
        drugaddinv = findViewById(R.id.add_inventory)
        drugreq = findViewById(R.id.drug_req)

    }

    fun inv(view: View){
//        val drugadd = Intent(this,DrugInventory::class.java)
        val drugadd = Intent(this,findinventry::class.java)
        startActivity(drugadd)
        finish()
    }

    fun pha_account(view: View){
        val prof = Intent(this,pha_myaccount::class.java)
        startActivity(prof)
        finish()
    }

    fun add_inv(view: View){
        val addDrug = Intent(this,AddDrugInventory::class.java)
        startActivity(addDrug)
        finish()
    }

    fun dug_reqs(view: View){
        val cus_drugs = Intent(this,pha_chat_to_customer::class.java)
        startActivity(cus_drugs)
        finish()
    }

}