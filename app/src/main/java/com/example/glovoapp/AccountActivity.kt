package com.example.glovoapp

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.text.InputType
import android.view.Gravity
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.glovoapp.databinding.ActivityAccountBinding

class AccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Handle the back button click
        binding.backButton.setOnClickListener {
            finish() // Close the current activity and go back to the previous one
        }

        // Set click listener for Change Password
        binding.changePassword.setOnClickListener {
            showChangePasswordDialog()
        }
    }

    private fun showChangePasswordDialog() {
        // Create EditTexts for current and new password
        val currentPasswordEdit = EditText(this).apply {
            hint = "Current password"
            inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
        val newPasswordEdit = EditText(this).apply {
            hint = "New password"
            inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }

        // Create a linear layout to hold both EditTexts
        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(50, 20, 50, 20)
            addView(currentPasswordEdit)
            addView(newPasswordEdit)
        }

        // Create an AlertDialog builder
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Change Password")
        builder.setView(layout) // Set the layout with both EditTexts

        // Set dialog buttons with custom styling for "Done"
        builder.setPositiveButton("Done") { dialog, _ ->
            val currentPassword = currentPasswordEdit.text.toString().trim()
            val newPassword = newPasswordEdit.text.toString().trim()

            // TODO: Implement your password change logic

            dialog.dismiss() // Dismiss the dialog after the action
        }
        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.cancel() // Cancel the dialog
        }

        // Create and show the dialog
        val dialog = builder.create()
        dialog.show()

        // Customizing the "Done" button
        dialog.getButton(AlertDialog.BUTTON_POSITIVE)?.apply {
            setTextColor(Color.parseColor("#008C71"))
            setTypeface(typeface, android.graphics.Typeface.BOLD) // Bold text
        }
    }
}
