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

/*package com.example.glovoapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

        displayCartItems()
    }
    private fun displayCartItems() {
        val cartItems = CartRepository.getCartItems().toMutableList()
        val totalAmount = CartRepository.getTotalAmount()

        binding.cartRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.cartRecyclerView.adapter = CartAdapter(cartItems) {
            // Update total amount when cart items change
            val newTotalAmount = CartRepository.getTotalAmount()
            binding.totalAmount.text = "Total: $${String.format("%.2f", newTotalAmount)}"
        }

        // Set initial total amount
        binding.totalAmount.text = "Total: $${String.format("%.2f", totalAmount)}"
    }

}*/

/*package com.example.glovoapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.glovoapp.databinding.ActivityCartBinding
import com.example.glovoapp.models.CartItem
import com.example.glovoapp.repository.CartRepository

class CartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Handle the back button to close the cart page
        binding.backButton.setOnClickListener {
            finish()
        }

        // Display the cart items and total amount
        displayCartItems()
    }

    private fun displayCartItems() {
        val cartItems = CartRepository.getItems()

        if (cartItems.isEmpty()) {
            binding.cartItems.text = "No items in the cart yet"
            binding.totalAmount.text = "Total: $0.00"
        } else {
            val cartDetails = StringBuilder()
            var total = 0.0

            // Loop through the items in the cart and build the display string
            for (item in cartItems) {
                cartDetails.append("${item.name}: ${item.price} x ${item.quantity}\n")
                total += item.price * item.quantity
            }

            binding.cartItems.text = cartDetails.toString()
            binding.totalAmount.text = "Total: $${String.format("%.2f", total)}"
        }
    }
}*/

/*package com.example.glovoapp

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


package com.example.glovoapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.glovoapp.R
import com.example.glovoapp.models.CartItem

class CartAdapter(private val items: List<CartItem>) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName: TextView = itemView.findViewById(R.id.cart_item_name)
        val itemDescription: TextView = itemView.findViewById(R.id.cart_item_description)
        val itemPrice: TextView = itemView.findViewById(R.id.cart_item_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = items[position]
        holder.itemName.text = item.name
        holder.itemDescription.text = item.description
        holder.itemPrice.text = "$${item.price}"
    }

    override fun getItemCount(): Int {
        return items.size
    }
}*/


