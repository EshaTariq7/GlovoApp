

package com.example.glovoapp

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.InputType
import android.view.Gravity
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.glovoapp.databinding.ActivityAccountBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest

class AccountActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAccountBinding
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Load user details from Firebase
        loadUserDetails()

        // Handle the back button click
        binding.backButton.setOnClickListener {
            finish() // Close the current activity and go back to the previous one
        }

        // Set click listener for Save Changes Button
        binding.saveChangesButton.setOnClickListener {
            val updatedName = binding.usernameEdit.text.toString()
            val updatedEmail = binding.emailEdit.text.toString()

            if (updatedName.isEmpty() || updatedEmail.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                updateUserDetails(updatedName, updatedEmail)
                Toast.makeText(this, "Details updated successfully", Toast.LENGTH_SHORT).show()
            }
        }

        // Set click listener for Change Password
        binding.changePassword.setOnClickListener {
            showChangePasswordDialog()
        }
    }

    // Load user details from Firebase Authentication
    private fun loadUserDetails() {
        val user = auth.currentUser

        user?.let {
            // Safe access of nullable properties with Elvis operator
            val displayName = it.displayName ?: "No Name"
            val email = it.email ?: "No Email"

            // Set the user details in the EditText fields
            binding.usernameEdit.setText(displayName)
            binding.emailEdit.setText(email)
        }
    }
    // Update user details in Firebase Authentication
    private fun updateUserDetails(name: String, email: String) {
        val user = auth.currentUser

        user?.let {
            // Update user profile (display name)
            val profileUpdates = UserProfileChangeRequest.Builder()
                .setDisplayName(name) // Setting the new display name
                .build()

            it.updateProfile(profileUpdates).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Profile updated successfully, but no Toast message
                }
            }

            // Update email
            it.updateEmail(email).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Email updated successfully, but no Toast message
                }
            }
        }
    }




    // Show the dialog to change password
    private fun showChangePasswordDialog() {
        val currentPasswordEdit = EditText(this).apply {
            hint = "Current password"
            inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
        val newPasswordEdit = EditText(this).apply {
            hint = "New password"
            inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(50, 20, 50, 20)
            addView(currentPasswordEdit)
            addView(newPasswordEdit)
        }

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Change Password")
        builder.setView(layout)

        builder.setPositiveButton("Done") { dialog, _ ->
            val currentPassword = currentPasswordEdit.text.toString().trim()
            val newPassword = newPasswordEdit.text.toString().trim()

            // TODO: Implement your password change logic here
            dialog.dismiss()
        }
        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.cancel()
        }

        val dialog = builder.create()
        dialog.show()

        dialog.getButton(AlertDialog.BUTTON_POSITIVE)?.apply {
            setTextColor(Color.parseColor("#008C71"))
            setTypeface(typeface, android.graphics.Typeface.BOLD)
        }
    }
}



/*package com.example.glovoapp

import android.os.Bundle
import android.text.InputType
import android.view.Gravity
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.glovoapp.databinding.ActivityAccountBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class AccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAccountBinding
    private lateinit var auth: FirebaseAuth
    private var currentUser: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        currentUser = auth.currentUser

        // Load user details from Firebase
        loadUserDetails()

        // Handle Save Changes button click
        binding.saveChangesButton.setOnClickListener {
            val updatedName = binding.usernameEdit.text.toString()
            val updatedEmail = binding.emailEdit.text.toString()

            if (updatedName.isEmpty() || updatedEmail.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                updateUserDetails(updatedName, updatedEmail)
            }
        }

        // Handle Change Password
        binding.changePassword.setOnClickListener {
            showChangePasswordDialog()
        }
    }

    private fun loadUserDetails() {
        currentUser?.let { user ->
            binding.usernameEdit.setText(user.displayName ?: "N/A")
            binding.emailEdit.setText(user.email ?: "N/A")
            binding.phoneEdit.setText(user.phoneNumber ?: "N/A")
        } ?: run {
            Toast.makeText(this, "No user logged in", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUserDetails(name: String, email: String) {
        currentUser?.let { user ->
            val profileUpdates = com.google.firebase.auth.UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .build()

            user.updateProfile(profileUpdates).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Name updated successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Failed to update name", Toast.LENGTH_SHORT).show()
                }
            }

            user.updateEmail(email).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Email updated successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Failed to update email: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun showChangePasswordDialog() {
        val newPasswordEdit = EditText(this).apply {
            hint = "New password"
            inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(50, 20, 50, 20)
            addView(newPasswordEdit)
        }

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Change Password")
        builder.setView(layout)

        builder.setPositiveButton("Done") { _, _ ->
            val newPassword = newPasswordEdit.text.toString().trim()
            if (newPassword.isNotEmpty()) {
                updatePassword(newPassword)
            } else {
                Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }
        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.cancel()
        }

        val dialog = builder.create()
        dialog.show()
    }

    private fun updatePassword(newPassword: String) {
        currentUser?.let { user ->
            user.updatePassword(newPassword).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Password updated successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Failed to update password: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}*/



/*package com.example.glovoapp

import android.app.AlertDialog
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.text.InputType
import android.view.Gravity
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
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

        // Retrieve user details from SharedPreferences
        val sharedPreferences = getSharedPreferences("USER_PREFS", MODE_PRIVATE)
        val name = sharedPreferences.getString("USER_NAME", "N/A")
        val email = sharedPreferences.getString("USER_EMAIL", "N/A")
        val phoneNumber = sharedPreferences.getString("USER_PHONE", "N/A")

        // Display the user details in EditText
        binding.usernameEdit.setText(name)
        binding.emailEdit.setText(email)
        binding.phoneEdit.setText(phoneNumber)
        // Set click listener for Save Changes Button
        binding.saveChangesButton.setOnClickListener {
            val updatedName = binding.usernameEdit.text.toString()
            val updatedEmail = binding.emailEdit.text.toString()
            val updatedPhone = binding.phoneEdit.text.toString()

            if (updatedName.isEmpty() || updatedEmail.isEmpty() || updatedPhone.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                saveUserDetails(updatedName, updatedEmail, updatedPhone)
                Toast.makeText(this, "Details updated successfully", Toast.LENGTH_SHORT).show()
            }
        }

        // Set click listener for Change Password
        binding.changePassword.setOnClickListener {
            showChangePasswordDialog()
        }
    }
    private fun saveUserDetails(name: String, email: String, phone: String) {
        val sharedPreferences: SharedPreferences = getSharedPreferences("USER_PREFS", MODE_PRIVATE)
        sharedPreferences.edit()
            .putString("USER_NAME", name)
            .putString("USER_EMAIL", email)
            .putString("USER_PHONE", phone)
            .apply()
    }

    private fun showChangePasswordDialog() {
        val currentPasswordEdit = EditText(this).apply {
            hint = "Current password"
            inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
        val newPasswordEdit = EditText(this).apply {
            hint = "New password"
            inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(50, 20, 50, 20)
            addView(currentPasswordEdit)
            addView(newPasswordEdit)
        }

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Change Password")
        builder.setView(layout)

        builder.setPositiveButton("Done") { dialog, _ ->
            val currentPassword = currentPasswordEdit.text.toString().trim()
            val newPassword = newPasswordEdit.text.toString().trim()

            // TODO: Implement your password change logic

            dialog.dismiss()
        }
        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.cancel()
        }

        val dialog = builder.create()
        dialog.show()

        dialog.getButton(AlertDialog.BUTTON_POSITIVE)?.apply {
            setTextColor(Color.parseColor("#008C71"))
            setTypeface(typeface, android.graphics.Typeface.BOLD)
        }
    }
}*/

/*package com.example.glovoapp

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
}*/
