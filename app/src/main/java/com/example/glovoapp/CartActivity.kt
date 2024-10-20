
package com.example.glovoapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.glovoapp.databinding.ActivityCartBinding

class CartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Example: Handle the back button to close the cart page
        binding.backButton.setOnClickListener {
            finish()
        }

        // Example: Displaying cart items (you would replace this with real data)
        displayCartItems()
    }

    private fun displayCartItems() {
        // This function should load cart items from your cart data source.
        // For now, you could display some placeholder text or items.
        binding.cartItems.text = "Your cart is empty."
    }
}
