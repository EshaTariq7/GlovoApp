package com.example.glovoapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.glovoapp.databinding.ActivityCardInfoBinding

class CardInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCardInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCardInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up the back button to finish the activity
        binding.backButton.setOnClickListener {
            finish() // Close the activity and go back
        }

        // Automatically format expiry date as MM/YY
        binding.cardExpiry.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrEmpty()) {
                    val input = s.toString()
                    if (input.length == 2 && before == 0) {
                        binding.cardExpiry.setText("$input/")
                        binding.cardExpiry.setSelection(binding.cardExpiry.text.length)
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        // Save button action
        binding.submitCardInfoButton.setOnClickListener {
            val name = binding.cardName.text.toString()
            val number = binding.cardNumber.text.toString()
            val expiry = binding.cardExpiry.text.toString()
            val cvc = binding.cardCVC.text.toString()
            if (validateInputs(name, number, expiry, cvc)) {
                // Proceed with payment processing
                Toast.makeText(this, "Card details submitted successfully!", Toast.LENGTH_SHORT).show()
            }


        }
    }

    private fun validateInputs(name: String, number: String, expiry: String, cvc: String): Boolean {
        if (name.isEmpty()) {
            binding.cardName.error = "Name on card is required"
            return false
        }

        if (number.isEmpty() || number.length != 16 || !number.all { it.isDigit() }) {
            binding.cardNumber.error = "Please provide a valid 16-digit card number"
            return false
        }

        if (expiry.isEmpty() || !expiry.matches(Regex("(0[1-9]|1[0-2])/(\\d{2})"))) {
            binding.cardExpiry.error = "Please provide a valid expiry date (MM/YY)"
            return false
        }

        if (cvc.isEmpty() || cvc.length != 3 || !cvc.all { it.isDigit() }) {
            binding.cardCVC.error = "Please provide a valid 3-digit CVC"
            return false
        }

        return true
    }
}

