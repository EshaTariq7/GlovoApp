package com.example.glovoapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.glovoapp.databinding.ActivitySplashBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.FirebaseApp

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)

        // Set up binding
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Check Firebase authentication state after splash delay
        Handler(Looper.getMainLooper()).postDelayed({
            val user = FirebaseAuth.getInstance().currentUser

            if (user != null) {
                // User is authenticated
                val intent = Intent(this, StoresActivity::class.java)
                startActivity(intent)
            } else {
                // User is not authenticated, navigate to login screen
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            finish() // Close splash activity
        }, 3000) // 3 seconds delay
    }
}


