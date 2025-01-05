package com.example.glovoapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class UserLoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_login)

        auth = FirebaseAuth.getInstance()

        val nameInput = findViewById<EditText>(R.id.edit_name)
        val emailInput = findViewById<EditText>(R.id.edit_email)
        val passwordInput = findViewById<EditText>(R.id.edit_password)
        val submitButton = findViewById<Button>(R.id.btn_submit)

        submitButton.setOnClickListener {
            val userName = nameInput.text.toString()
            val userEmail = emailInput.text.toString()
            val userPassword = passwordInput.text.toString()

            if (userName.isEmpty() || userEmail.isEmpty() || userPassword.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                registerUser(userName, userEmail, userPassword)
            }
        }
    }

    private fun registerUser(userName: String, userEmail: String, userPassword: String) {
        auth.createUserWithEmailAndPassword(userEmail, userPassword)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Save user name in Firebase
                    val user = auth.currentUser
                    val profileUpdates = com.google.firebase.auth.UserProfileChangeRequest.Builder()
                        .setDisplayName(userName)
                        .build()

                    user?.updateProfile(profileUpdates)?.addOnCompleteListener { updateTask ->
                        if (updateTask.isSuccessful) {
                            navigateToStoresPage()
                        } else {
                            Toast.makeText(this, "Failed to update profile", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(this, "Registration failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun navigateToStoresPage() {
        val intent = Intent(this, StoresActivity::class.java)
        startActivity(intent)
        finish()
    }
}


