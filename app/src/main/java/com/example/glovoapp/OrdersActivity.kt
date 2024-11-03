package com.example.glovoapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.glovoapp.databinding.ActivityOrdersBinding

class OrdersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrdersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrdersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Handle the back button click
        binding.backButton.setOnClickListener {
            finish() // Close the current activity and go back to the previous one
        }
    }
}
