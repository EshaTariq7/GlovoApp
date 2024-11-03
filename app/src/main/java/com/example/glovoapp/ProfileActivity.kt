package com.example.glovoapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.glovoapp.databinding.ActivityProfileBinding
import android.graphics.Typeface

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            finish() // Close the current activity and go back to the previous one
        }

        // Navigate to Orders page when profile image is clicked
        binding.profileImage.setOnClickListener {
            val intent = Intent(this, OrdersActivity::class.java)
            startActivity(intent)
        }

        // Navigate to Account page when account image is clicked
        binding.accImage.setOnClickListener {
            val intent = Intent(this, AccountActivity::class.java)
            startActivity(intent)
        }

        // Navigate to Language page when language image is clicked
        binding.languageImage.setOnClickListener {
            val intent = Intent(this, LanguageActivity::class.java)
            startActivity(intent)
        }

        // Show logout confirmation dialog when logout image is clicked
        binding.logoutImage.setOnClickListener {
            showLogoutConfirmationDialog()
        }
    }

    // Method to show the logout confirmation dialog
    private fun showLogoutConfirmationDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle("Oops!")
            .setMessage("Are you sure you want to log out?")
            .setPositiveButton("Logout") { _, _ ->
                // Navigate to LoginActivity on logout
                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                finish()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                // Dismiss the dialog and stay on the profile page
                dialog.dismiss()
            }

        // Create and show the dialog
        val dialog = dialogBuilder.create()
        dialog.show()

        // Apply green color and bold text to buttons
        dialog.getButton(AlertDialog.BUTTON_POSITIVE)?.apply {
            setTextColor(resources.getColor(R.color.glovo_green, theme))
            setTypeface(null, Typeface.BOLD)
        }

        dialog.getButton(AlertDialog.BUTTON_NEGATIVE)?.apply {
            setTextColor(resources.getColor(R.color.glovo_green, theme))
            setTypeface(null, Typeface.BOLD)
        }
    }
}
