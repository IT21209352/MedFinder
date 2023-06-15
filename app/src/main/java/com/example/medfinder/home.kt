package com.example.medfinder

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.medfinder.databinding.ActivityHomeBinding
import com.example.medfinder.ui.dashboard.DashboardFragment

class home : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private var pha = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_home)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications,R.id.cus_myaccount
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

       /* near_drug = findViewById(R.id.find_near_drug)*/




    }

    /*fun serch(view: View){
        val neardrug = Intent(this,DashboardFragment::class.java)
        startActivity(neardrug)
        finish()
    }*/
}