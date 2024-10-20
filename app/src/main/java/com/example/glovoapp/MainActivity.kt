package com.example.glovoapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.glovoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
            // Navigate to Login Activity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        // Navigate to Add Delivery Address Activity when address input is clicked
        binding.addressInput.setOnClickListener {
            val intent = Intent(this, AddDeliveryAddressActivity::class.java)
            startActivity(intent)
        }


        // Set up the click listener for the "Explore Stores" button
        binding.exploreStore.setOnClickListener {
            openStoresActivity()  // Call the function to open StoresActivity
        }
    }

    // Method to open StoresActivity when the button is clicked
    private fun openStoresActivity() {
        val intent = Intent(this, StoresActivity::class.java)
        startActivity(intent)
    }
    }


