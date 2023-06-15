package com.example.medfinder.ui.notifications

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.medfinder.R
import com.example.medfinder.User
import com.example.medfinder.adapters.UserAdapter
import com.example.medfinder.databinding.FragmentNotificationsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class NotificationsFragment : Fragment() {

    lateinit var userRecyclerView:RecyclerView
    private lateinit var  userList: ArrayList<User>
    private lateinit var adapter: UserAdapter
    private lateinit var  auth:FirebaseAuth
    private lateinit var mDbRef:DatabaseReference


    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var pha = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        val context: Context = requireContext()
        auth= FirebaseAuth.getInstance()
        mDbRef=FirebaseDatabase.getInstance().getReference()

        userList =ArrayList()
        adapter= UserAdapter(context,userList )

        val view = inflater.inflate(R.layout.fragment_notifications, container, false)

        userRecyclerView=view.findViewById(R.id.userRecyclerView)

        val layoutManager = LinearLayoutManager(context)
        userRecyclerView.layoutManager = layoutManager

        userRecyclerView.adapter=adapter

        mDbRef.child("user").addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                userList.clear()
               for(postSnapshot in snapshot.children){
                   val currentUser=postSnapshot.getValue(User::class.java)
                   if (auth.currentUser?.uid!=currentUser?.uid ){     // currentUser.type=="user"
                       userList.add(currentUser!!)
                   }

               }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })


        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)



        println(view)
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val context: Context = requireContext()
//        auth= FirebaseAuth.getInstance()
//        userList =ArrayList()
//        adapter=UserAdapter(context,userList )




    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}