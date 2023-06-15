package com.example.medfinder

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.medfinder.adapters.medicineAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference

class pha_chat_to_customer : AppCompatActivity() {

    private lateinit var getname: EditText
    private lateinit var getdescription: EditText
    private lateinit var auth: FirebaseAuth


    lateinit var uploadImageBtn: Button
    lateinit var imageView: ImageView
    var fileUri: Uri? = null;

    lateinit var userRecyclerView: RecyclerView
    private lateinit var  userList: ArrayList<medicine>
    private lateinit var adapter: medicineAdapter
    private lateinit var mDbRef: DatabaseReference
    private lateinit var uuid:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pha_chat_to_customer)


    }
}