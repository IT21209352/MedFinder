package com.example.medfinder

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File
import java.util.*


class Advertisment_add : AppCompatActivity() {

    lateinit var discription:EditText
    lateinit var bck_to_adv_home:ImageButton
    lateinit var chooseImageBtn: Button
    lateinit var uploadImageBtn: Button
    lateinit var imageView: ImageView
    lateinit var img :String
    private lateinit var mDbRef: DatabaseReference
    var fileUri: Uri? = null;




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.advertisment_add)
        // on below line initializing variables for buttons and image view.
        chooseImageBtn = findViewById(R.id.idBtnChooseImage)
        uploadImageBtn = findViewById(R.id.idBtnUploadImage)
        imageView = findViewById(R.id.idIVImage)
        discription=findViewById(R.id.dis)
        bck_to_adv_home=findViewById(R.id.bck_to_adv_home)

        bck_to_adv_home.setOnClickListener{
            val intent = Intent(this, Advertisments::class.java)
            startActivity(intent)
        }

        discription.setText(intent.getStringExtra("dis"))
         img = intent.getStringExtra("id").toString()

        if (img!=null){
            val strogeRef = FirebaseStorage.getInstance().reference.child(img!!)

            val localfile= File.createTempFile("tempImage","jpg")
            strogeRef.getFile(localfile).addOnSuccessListener {

                val bitmap= BitmapFactory.decodeFile(localfile.absolutePath)
                imageView.setImageBitmap(bitmap)


            }.addOnFailureListener{

            }

        }




        // on below line adding click listener for our choose image button.
        chooseImageBtn.setOnClickListener {
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

        // on below line adding click listener to upload image.
        uploadImageBtn.setOnClickListener {
            // on below line calling upload image button to upload our image.
            uploadImage()
        }
    }

    // on below line adding on activity result method this method is called when user picks the image.
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // on below line we are checking if the result is ok
        if (requestCode == 22 && resultCode == RESULT_OK && data != null && data.data != null) {
            // on below line initializing file uri with the data which we get from intent
            fileUri = data.data
            try {
                // on below line getting bitmap for image from file uri.
                val bitmap: Bitmap = MediaStore.Images.Media.getBitmap(contentResolver, fileUri);
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
            var uuid=UUID.randomUUID().toString()
            val ref: StorageReference = FirebaseStorage.getInstance().getReference()
                .child(uuid)

            println(uuid)
            var dis=discription.text.toString()
            mDbRef= FirebaseDatabase.getInstance().getReference()
            if (img!=null) {
                mDbRef.child("image").child(img).removeValue()
            }

            mDbRef.child("image").child(uuid).setValue(advertisment(uuid,dis))


            // on below line adding a file to our storage.
            ref.putFile(fileUri!!).addOnSuccessListener {
                // this method is called when file is uploaded.
                // in this case we are dismissing our progress dialog and displaying a toast message
                progressDialog.dismiss()
                Toast.makeText(applicationContext, "Image Uploaded..", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, Advertisments::class.java)
                startActivity(intent)

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