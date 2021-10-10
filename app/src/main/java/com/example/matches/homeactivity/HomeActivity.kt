package com.example.matches.homeactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.matches.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        var bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        var navController = Navigation.findNavController(this@HomeActivity,R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(bottomNavigationView,navController)
    }
}