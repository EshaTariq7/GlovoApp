/*package com.example.glovoapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.glovoapp.adapters.CartAdapter
import com.example.glovoapp.databinding.ActivityCartBinding
import com.example.glovoapp.repository.CartRepository

class CartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCartBinding
    private lateinit var cartRepository: CartRepository // Use instance for CartRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize binding and repository
        binding = ActivityCartBinding.inflate(layoutInflater)
        cartRepository = CartRepository(this)

        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            finish()
        }

        binding.checkoutButton.setOnClickListener {
            proceedToCheckout()
        }

        displayCartItems()
    }



    private fun displayCartItems() {
        val cartItems = cartRepository.getCartItems().toMutableList() // Retrieve items from the database
        updateTotalAmount()

        binding.cartRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.cartRecyclerView.adapter = CartAdapter(this, cartItems) {
            // Update the total amount when cart items change
            updateTotalAmount()
        }

        // Update the visibility of the RecyclerView and the empty cart message
        if (cartItems.isEmpty()) {
            binding.cartRecyclerView.visibility = View.GONE
            binding.emptyCartMessage.visibility = View.VISIBLE
        } else {
            binding.cartRecyclerView.visibility = View.VISIBLE
            binding.emptyCartMessage.visibility = View.GONE
        }
    }

    private fun updateTotalAmount() {
        val newTotalAmount = cartRepository.getTotalAmount()
        binding.totalAmount.text = "Total: $${String.format("%.2f", newTotalAmount)}"
    }

    private fun proceedToCheckout() {
        val cartItems = cartRepository.getCartItems()
        if (cartItems.isNotEmpty()) {
            val intent = Intent(this, CheckoutActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(
                this,
                "Your cart is empty. Add items before proceeding to checkout.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}*/

package com.example.glovoapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.glovoapp.adapters.CartAdapter
import com.example.glovoapp.databinding.ActivityCartBinding
import com.example.glovoapp.repository.CartRepository

class CartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            finish()
        }

        binding.checkoutButton.setOnClickListener {
            proceedToCheckout()
        }

        displayCartItems()
    }

    private fun displayCartItems() {
        val cartItems = CartRepository.getCartItems().toMutableList()
        updateTotalAmount()

        binding.cartRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.cartRecyclerView.adapter = CartAdapter(cartItems) {
            // Update the total amount when cart items change
            updateTotalAmount()
        }

        // Update the visibility of the RecyclerView and the empty cart message
        if (cartItems.isEmpty()) {
            binding.cartRecyclerView.visibility = View.GONE
            binding.emptyCartMessage.visibility = View.VISIBLE
        } else {
            binding.cartRecyclerView.visibility = View.VISIBLE
            binding.emptyCartMessage.visibility = View.GONE
        }
    }

    private fun updateTotalAmount() {
        val newTotalAmount = CartRepository.getTotalAmount()
        binding.totalAmount.text = "Total: $${String.format("%.2f", newTotalAmount)}"
    }

    private fun proceedToCheckout() {
        val cartItems = CartRepository.getCartItems()
        if (cartItems.isNotEmpty()) {
            val intent = Intent(this, CheckoutActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(
                this,
                "Your cart is empty. Add items before proceeding to checkout.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}




