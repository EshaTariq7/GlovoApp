package com.example.glovoapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.glovoapp.databinding.ActivityOrderConfirmationBinding

class OrderConfirmationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrderConfirmationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderConfirmationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.backButton.setOnClickListener {
            finish() // Close the activity and go back
        }


    }
}


