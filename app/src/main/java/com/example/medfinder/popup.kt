package com.example.medfinder

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.*

class popup : AppCompatActivity() {

    private lateinit var name: EditText
    private lateinit var dis:EditText
    private lateinit var  imageView:ImageView
    private lateinit var cancel:Button
    private lateinit var update:Button
    private lateinit var auth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference
    var fileUri: Uri? = null;
    private lateinit var uuid:String
//    private lateinit var uid:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popup)
        getSupportActionBar()?.hide()

        name=findViewById(R.id.drgname)
        dis=findViewById(R.id.drgDesc)
        cancel=findViewById(R.id.cancle)
        update=findViewById(R.id.update)
        imageView=findViewById(R.id.adImage)
        auth= FirebaseAuth.getInstance()
        mDbRef=FirebaseDatabase.getInstance().getReference()

        val name1=intent.getStringExtra("name")
        val dis1=intent.getStringExtra("dis")
        val img= intent.getStringExtra("img")
        uuid=img.toString()
//
        name.setText(name1)
        dis.setText(dis1)

        cancel.setOnClickListener {
            val intent= Intent(this, home::class.java)


                this.startActivity(intent)
        }

        update.setOnClickListener {

            uploadImage()
            val name=name.text.toString()
            val discription=dis.text.toString()

            addUserToDatabase(name,discription,auth.currentUser?.uid!!)

        }
        imageView.setOnClickListener {
            // on below line calling intent to get our image from phone storage.
            val intent = Intent()
            // on below line setting type of files which we want to pick in our case we are picking images.
            intent.type = "image/*"
            // on below line we are setting action to get content
            intent.action = Intent.ACTION_GET_CONTENT
            // on below line calling start activity for result to choose image.
            startActivityForResult(
                // on below line creating chooser to choose image.
                Intent.createChooser(
                    intent,
                    "Pick your image to upload"
                ),
                22
            )
        }


    }

    private fun addUserToDatabase(name:String,description:String,uid:String){


        mDbRef= FirebaseDatabase.getInstance().getReference()


        mDbRef.child("medicine").child(uid).child(intent.getStringExtra("uid")!!).setValue(medicine(name, description, intent.getStringExtra("uid")!!,uuid))

        val intent= Intent(this, home::class.java)
        this.startActivity(intent)


    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // on below line we are checking if the result is ok
        if (requestCode == 22 && resultCode == AppCompatActivity.RESULT_OK && data != null && data.data != null) {
            // on below line initializing file uri with the data which we get from intent
            fileUri = data.data
            try {
                // on below line getting bitmap for image from file uri.
                val bitmap: Bitmap = MediaStore.Images.Media.getBitmap( contentResolver, fileUri);
                // on below line setting bitmap for our image view.
                imageView.setImageBitmap(bitmap)
            } catch (e: Exception) {
                // handling exception on below line
                e.printStackTrace()
            }
        }
    }


    // on below line creating a function to upload our image.
    fun uploadImage() {
        // on below line checking weather our file uri is null or not.
        if (fileUri != null) {
            // on below line displaying a progress dialog when uploading an image.
            val progressDialog = ProgressDialog(this)
            // on below line setting title and message for our progress dialog and displaying our progress dialog.
            progressDialog.setTitle("Uploading...")
            progressDialog.setMessage("Uploading your image..")
            progressDialog.show()

            // on below line creating a storage refrence for firebase storage and creating a child in it with
            // random uuid.
            uuid= UUID.randomUUID().toString()
            val ref: StorageReference = FirebaseStorage.getInstance().getReference()
                .child(uuid)

//            println(uuid)
//            mDbRef= FirebaseDatabase.getInstance().getReference()
//            mDbRef.child("image").child(uuid).setValue(advertisment(uuid,""))


            // on below line adding a file to our storage.
            ref.putFile(fileUri!!).addOnSuccessListener {
                // this method is called when file is uploaded.
                // in this case we are dismissing our progress dialog and displaying a toast message
                progressDialog.dismiss()
                Toast.makeText(applicationContext, "Image Uploaded..", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                // this method is called when there is failure in file upload.
                // in this case we are dismissing the dialog and displaying toast message
                progressDialog.dismiss()
                Toast.makeText(applicationContext, "Fail to Upload Image..", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

}