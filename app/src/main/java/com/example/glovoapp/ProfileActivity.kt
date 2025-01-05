package com.example.glovoapp

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.glovoapp.databinding.ActivityProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var auth: FirebaseAuth
    private var currentUser: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        currentUser = auth.currentUser

        // Retrieve the user's name from Firebase
        loadUserDetails()

        // Handle the back button click
        binding.backButton.setOnClickListener {
            finish() // Go back to the previous screen
        }

        // Profile image navigation
        binding.profileImage.setOnClickListener {
            val intent = Intent(this, OrdersActivity::class.java)
            startActivity(intent)
        }

        binding.accImage.setOnClickListener {
            val intent = Intent(this, AccountActivity::class.java)
            startActivity(intent)
        }

        binding.languageImage.setOnClickListener {
            val intent = Intent(this, LanguageActivity::class.java)
            startActivity(intent)
        }

        // Show logout dialog
        binding.logoutImage.setOnClickListener {
            showLogoutConfirmationDialog()
        }
    }

    private fun loadUserDetails() {
        currentUser?.let { user ->
            // Display only the user's name in the greeting
            binding.greetingText.text = "Hello, ${user.displayName ?: "User"}!"
        } ?: run {
            // If the user is not authenticated
            binding.greetingText.text = "Hello, Guest!"
        }
    }

    private fun showLogoutConfirmationDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle("Oops!")
            .setMessage("Are you sure you want to log out?")
            .setPositiveButton("Logout") { _, _ ->
                clearLoginState()
                navigateToLoginScreen()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }

        val dialog = dialogBuilder.create()
        dialog.show()

        dialog.getButton(AlertDialog.BUTTON_POSITIVE)?.apply {
            setTextColor(resources.getColor(R.color.glovo_green, theme))
            setTypeface(null, Typeface.BOLD)
        }

        dialog.getButton(AlertDialog.BUTTON_NEGATIVE)?.apply {
            setTextColor(resources.getColor(R.color.glovo_green, theme))
            setTypeface(null, Typeface.BOLD)
        }
    }

    private fun clearLoginState() {
        // Firebase will handle the logout; no need to clear SharedPreferences for Firebase-authenticated users
        auth.signOut()
    }

    private fun navigateToLoginScreen() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }
}



