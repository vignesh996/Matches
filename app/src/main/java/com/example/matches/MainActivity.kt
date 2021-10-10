package com.example.matches

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.matches.homeactivity.HomeActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var button = findViewById<ImageView>(R.id.img_next)
        button.setOnClickListener {
            var intent = Intent(this@MainActivity,HomeActivity::class.java)
            startActivity(intent)

        }
    }

    override fun onStop() {
        super.onStop()
        finish()
    }
}