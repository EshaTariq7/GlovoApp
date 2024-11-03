package com.example.glovoapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.glovoapp.databinding.ActivityCardInfoBinding

class CardInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCardInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the binding
        binding = ActivityCardInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up the back button to finish the activity
        binding.backButton.setOnClickListener {
            finish() // Close the activity and go back
        }

        // Reference EditText views through binding
        val cardName = binding.cardName
        val cardNumber = binding.cardNumber
        val cardExpiry = binding.cardExpiry
        val cardCVC = binding.cardCVC
        val submitButton = binding.submitCardInfoButton

        submitButton.setOnClickListener {
            // Collect card details and proceed with payment processing
            val name = cardName.text.toString()
            val number = cardNumber.text.toString()
            val expiry = cardExpiry.text.toString()
            val cvc = cardCVC.text.toString()

            // Validate and process payment here
            // (You might want to return to CheckoutActivity with card info)
        }
    }
}
