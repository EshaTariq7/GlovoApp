


package com.example.glovoapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.UserProfileChangeRequest

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private val RC_SIGN_IN = 9001 // Google Sign-In result request code

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        val phoneInput = findViewById<EditText>(R.id.edit_phone_number)
        val loginButton = findViewById<Button>(R.id.btn_login)
        val existingAccountText = findViewById<TextView>(R.id.existing_account_text)
        val googleSignInButton = findViewById<Button>(R.id.btn_google) // Google sign-in button

        // Phone login button functionality
        loginButton.setOnClickListener {
            val phoneNumber = phoneInput.text.toString()
            if (phoneNumber.length == 11 && phoneNumber.all { it.isDigit() }) {
                // Continue with phone login
                val intent = Intent(this, UserLoginActivity::class.java)
                intent.putExtra("PHONE_NUMBER", phoneNumber)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Enter a valid 11-digit phone number", Toast.LENGTH_SHORT).show()
            }
        }

        // Navigate to Firebase Login Activity
        existingAccountText.setOnClickListener {
            val intent = Intent(this, FirebaseLoginActivity::class.java)
            startActivity(intent)
        }

        // Google Sign-In button clicked
        googleSignInButton.setOnClickListener {
            signInWithGoogle()
        }
    }

    // Google Sign-In logic
    private fun signInWithGoogle() {
        // Configure Google Sign-In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id)) // Replace with your Web Client ID
            .requestEmail()
            .build()

        val googleSignInClient = GoogleSignIn.getClient(this, gso)
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    // Handle result from Google Sign-In
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign-In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account)
            } catch (e: ApiException) {
                Toast.makeText(this, "Google sign-in failed: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Authenticate with Firebase using the Google account
    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount?) {
        val credential: AuthCredential = GoogleAuthProvider.getCredential(account?.idToken, null)

        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Update the user's display name with the Google account name
                    val user = auth.currentUser
                    val profileUpdates = UserProfileChangeRequest.Builder()
                        .setDisplayName(account?.displayName)
                        .build()

                    user?.updateProfile(profileUpdates)?.addOnCompleteListener { updateTask ->
                        if (updateTask.isSuccessful) {
                            // Navigate to the next screen after successful login
                            val intent = Intent(this, StoresActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this, "Failed to update profile.", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show()
                }
            }
    }
}

