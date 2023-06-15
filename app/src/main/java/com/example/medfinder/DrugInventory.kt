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

class DrugInventory : AppCompatActivity() {

    lateinit var bck_to_pha_hme:ImageButton
    private lateinit var update :Button
    private lateinit var delete:Button
    private lateinit var name  : EditText
    private lateinit var dosage:EditText
    private lateinit var qty   :EditText
    private lateinit var price :EditText
    private lateinit var exp    :EditText

    private lateinit var  auth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drug_inventory)

        mDbRef= FirebaseDatabase.getInstance().getReference()
        name = findViewById(R.id.edtDrugName)
        dosage =findViewById(R.id.edtDrugDosage)
        qty =findViewById(R.id.edtDrugQunatity)
        price= findViewById(R.id.edtDrugPrice)
        exp  =findViewById(R.id.edtDrugExpDate)

        delete=findViewById(R.id.inv_del_drugs)
        update=findViewById(R.id.inv_add_drugs)
        auth= FirebaseAuth.getInstance()
        bck_to_pha_hme = findViewById(R.id.bck_btn_to_pha_home)
        val name1    =intent.getStringExtra("name")
        val dosage1  =intent.getStringExtra("dosage")
        val qty1     =intent.getStringExtra("qty")
        val price1   =intent.getStringExtra("price")
        val exp1    =intent.getStringExtra("exp")

        name.setText(name1)
        dosage.setText(dosage1)
        qty.setText(qty1)
        price.setText(price1)
        exp.setText(exp1)


        delete.setOnClickListener {

            mDbRef.child("inventory").child(auth.currentUser?.uid!!).child( intent.getStringExtra("i_id")!!).removeValue()
            val intent = Intent(this, pharmacy_home::class.java)
//

            this.startActivity(intent)
        }

        update.setOnClickListener {
            var name=name.text.toString()
            var dosage =dosage .text.toString()
           var qty= qty .text.toString()
            var price =price.text.toString()
           var  exp= exp.text.toString()

            mDbRef= FirebaseDatabase.getInstance().getReference()
            auth= FirebaseAuth.getInstance()

            var uid= auth.currentUser?.uid
            var key= intent.getStringExtra("i_id").toString()

            mDbRef.child("inventory").child(uid!!).child(key).setValue(inventry(key,name,dosage,qty,price,exp ))
            val intent = Intent(this, pharmacy_home::class.java)
//

            this.startActivity(intent)


        }

    }

    fun bck_To_phaHome(view:View){
        val bck_pha_home = Intent(this,pharmacy_home::class.java)
        startActivity(bck_pha_home)
        finish()
    }

}