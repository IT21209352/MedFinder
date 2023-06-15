package com.example.medfinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddDrugInventory : AppCompatActivity() {

    lateinit var bck_to_pha_hme_drug_add: ImageButton

    lateinit var add: Button
    private lateinit var getname: EditText
    private lateinit var getdosage: EditText
    private lateinit var qty: EditText
    private lateinit var price: EditText
    private lateinit var exp: EditText


    private lateinit var auth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_drug_inventory)

        getSupportActionBar()?.hide()
        auth= FirebaseAuth.getInstance()
        add=findViewById(R.id.inv_add_drugs)
        getname=findViewById(R.id.addDrugName)
        getdosage=findViewById(R.id.addDrugDosage)
        qty=findViewById(R.id.addDrugQunatity)
        price=findViewById(R.id.addDrugPrice)
        exp=findViewById(R.id.addDrugExpDate)
        bck_to_pha_hme_drug_add = findViewById(R.id.bck_btn_to_pha_home_from_add)

        add.setOnClickListener {

            val name=getname.text.toString()
            val dosage= getdosage.text.toString()
           val qty1 =qty.text.toString()
            val price1 =price.text.toString()
            val exp1=exp.text.toString()

            adddataToDatabase(name,dosage,qty1, price1 ,exp1)
        }
    }

    fun bck_To_phaHome_drag_add(view: View){
        val bck_home_add_drug = Intent(this,pharmacy_home::class.java)
        startActivity(bck_home_add_drug)
        finish()
    }

    private fun adddataToDatabase(name:String,Dosage:String,qty:String,price: String,exp:String){


        mDbRef= FirebaseDatabase.getInstance().getReference()

        var uid= auth.currentUser?.uid
        var key= mDbRef.child("inventory").child(uid!!).push().key.toString()

        mDbRef.child("inventory").child(uid!!).child(key).setValue(inventry(key,name,Dosage,qty,price,exp ))
        val intent = Intent(this, pharmacy_home::class.java)
//

       this.startActivity(intent)


    }
}