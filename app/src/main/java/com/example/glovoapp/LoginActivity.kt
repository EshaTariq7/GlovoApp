package com.example.glovoapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnWhatsApp = findViewById<Button>(R.id.btn_whatsapp)
        val btnSms = findViewById<Button>(R.id.btn_sms)
        val phoneInput = findViewById<EditText>(R.id.edit_phone_number)

        btnWhatsApp.setOnClickListener {
            val phoneNumber = phoneInput.text.toString()
            if (phoneNumber.isEmpty()) {
                Toast.makeText(this, "Please enter phone number", Toast.LENGTH_SHORT).show()
            } else {
                // Logic to handle WhatsApp login
            }
        }

        btnSms.setOnClickListener {
            val phoneNumber = phoneInput.text.toString()
            if (phoneNumber.isEmpty()) {
                Toast.makeText(this, "Please enter phone number", Toast.LENGTH_SHORT).show()
            } else {
                // Logic to handle SMS login
            }
        }
    }
}
