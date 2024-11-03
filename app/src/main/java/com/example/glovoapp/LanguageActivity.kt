package com.example.glovoapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.glovoapp.databinding.ActivityLanguageBinding

class LanguageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLanguageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLanguageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Handle the back button click
        binding.backButton.setOnClickListener {
            finish() // Close the current activity and go back to the previous one
        }
    }
}
