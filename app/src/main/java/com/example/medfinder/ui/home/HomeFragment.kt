package com.example.medfinder.ui.home

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel
import com.example.medfinder.R
import com.example.medfinder.adapters.medicineAdapter
import com.example.medfinder.databinding.FragmentHomeBinding
import com.example.medfinder.medicine
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.*
import kotlin.collections.ArrayList


class HomeFragment : Fragment() {

    private lateinit var addbtn: Button
    private lateinit var getname: EditText
    private lateinit var getdescription: EditText
    private lateinit var auth: FirebaseAuth


    lateinit var uploadImageBtn: Button
    lateinit var imageView: ImageView
    var fileUri: Uri? = null;

    lateinit var userRecyclerView: RecyclerView
    private lateinit var  userList: ArrayList<medicine>
    private lateinit var adapter: medicineAdapter
    private lateinit var mDbRef:DatabaseReference
    private lateinit var uuid:String

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {


        val view = inflater.inflate(R.layout.fragment_home, container, false)

        addbtn=view.findViewById(R.id.adddrgtopharmacy)
        getname=view.findViewById(R.id.adddrgname)
        getdescription=view.findViewById(R.id.adddrgDesc)

        imageView=view.findViewById(R.id.adImage)


        val imagelist=ArrayList<SlideModel>()



        val strogeRef = FirebaseStorage.getInstance().reference



        imagelist.add(SlideModel("https://photos.app.goo.gl/BynpdKkNJrJ7Sqb99"))
        imagelist.add(SlideModel("https://www.google.com/imgres?imgurl=https%3A%2F%2Fimg.freepik.com%2Fpremium-photo%2Fimage-colorful-galaxy-sky-generative-ai_791316-9864.jpg%3Fw%3D2000&tbnid=bDQHFlj2977FaM&vet=12ahUKEwi3kM6_k-7-AhXGk9gFHRAlDBMQMygKegUIARDLAQ..i&imgrefurl=https%3A%2F%2Fwww.freepik.com%2Fpremium-photo%2Fimage-colorful-galaxy-sky-generative-ai_37741252.htm&docid=E45jElzzTOZhiM&w=2000&h=1333&q=image&ved=2ahUKEwi3kM6_k-7-AhXGk9gFHRAlDBMQMygKegUIARDLAQ"))
        val imageSlider=view.findViewById<ImageSlider>(R.id.image_slider)
        imageSlider.setImageList(imagelist)


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


        auth= FirebaseAuth.getInstance()

        addbtn.setOnClickListener {


            val name=getname.text.toString()
            val discription=getdescription.text.toString()

            if (name==""){
                Toast.makeText(context,"some error" , Toast.LENGTH_SHORT).show()
            }else{
                addUserToDatabase(name,discription,auth.currentUser?.uid!!)
            }

        }


        val context: Context = requireContext()
        auth= FirebaseAuth.getInstance()
        mDbRef=FirebaseDatabase.getInstance().getReference()

        userList =ArrayList()
        adapter=medicineAdapter(context,userList)

        userRecyclerView=view.findViewById(R.id.drag_finder)

        val layoutManager = LinearLayoutManager(context)

        userRecyclerView.layoutManager = layoutManager

        userRecyclerView.adapter=adapter

        var uid= auth.currentUser?.uid.toString()

        mDbRef.child("medicine").child(uid).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                userList.clear()
                for(postSnapshot in snapshot.children){
                    val currentUser=postSnapshot.getValue(medicine::class.java)

//                    println(postSnapshot.key)
//                    println(currentUser?.name)
                    userList.add(currentUser!!)


                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // on below line we are checking if the result is ok
        if (requestCode == 22 && resultCode == AppCompatActivity.RESULT_OK && data != null && data.data != null) {
            // on below line initializing file uri with the data which we get from intent
            fileUri = data.data
            try {
                // on below line getting bitmap for image from file uri.
                val bitmap: Bitmap = MediaStore.Images.Media.getBitmap( getContext()?.getContentResolver(), fileUri);
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
            val progressDialog = ProgressDialog(context)
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
                Toast.makeText(context, "Image Uploaded..", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                // this method is called when there is failure in file upload.
                // in this case we are dismissing the dialog and displaying toast message
                progressDialog.dismiss()
                Toast.makeText(context, "Fail to Upload Image..", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun addUserToDatabase(name:String,description:String,uid:String){

        uploadImage()
        mDbRef= FirebaseDatabase.getInstance().getReference()
        var key= mDbRef.child("medicine").child(uid!!).push().key.toString()
        mDbRef.child("medicine").child(uid!!).child(key).setValue(medicine(name, description,key,uuid))

        getname.setText("")
        getdescription.setText("")
        imageView.setImageResource(R.drawable.baseline_image_24)

    }


}