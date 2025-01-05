/*package com.example.glovoapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.glovoapp.databinding.ActivityCheckoutBinding
import com.example.glovoapp.models.CartItem
import com.example.glovoapp.repository.CartRepository

class CheckoutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheckoutBinding
    private lateinit var cartRepository: CartRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cartRepository = CartRepository(this) // Initialize the repository

        binding.backButton.setOnClickListener {
            finish() // Close the activity and go back
        }

        val savedAddress = loadAddressFromPreferences()
        if (!savedAddress.isNullOrEmpty()) {
            binding.deliveryDetails.text = savedAddress
        } else {
            binding.deliveryDetails.text = "No delivery address set"
        }

        binding.deliveryDetails.setOnClickListener {
            val intent = Intent(this, AddDeliveryAddressActivity::class.java)
            startActivity(intent) // Allow the user to change the address
        }

        displayOrderDetails()

        // Handle Confirm Order button click
        binding.confirmOrderButton.setOnClickListener {
            if (validateInputs()) {
                navigateToConfirmationPage()
            } else {
                Toast.makeText(this, "Please complete all fields before confirming the order.", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun loadAddressFromPreferences(): String? {
        val sharedPreferences = getSharedPreferences("AppPreferences", MODE_PRIVATE)
        return sharedPreferences.getString("DELIVERY_ADDRESS", null)
    }

    private fun displayOrderDetails() {
        val cartItems = cartRepository.getCartItems() // Use the repository instance
        val deliveryCharge = 2.99
        val serviceCharge = 1.00

        val orderDetailsText = buildOrderDetailsText(cartItems)
        binding.orderDetails.text = orderDetailsText

        val productsTotal = calculateProductsTotal(cartItems)
        binding.productsPrice.text = "$${String.format("%.2f", productsTotal)}"

        binding.deliveryPrice.text = "$${String.format("%.2f", deliveryCharge)}"
        binding.servicePrice.text = "$${String.format("%.2f", serviceCharge)}"

        val totalPrice = productsTotal + deliveryCharge + serviceCharge
        binding.totalPrice.text = "$${String.format("%.2f", totalPrice)}"
    }

    private fun buildOrderDetailsText(cartItems: List<CartItem>): String {
        val stringBuilder = StringBuilder()
        for (item in cartItems) {
            stringBuilder.append("${item.name} x${item.quantity} - $${String.format("%.2f", item.price * item.quantity)}\n")
        }
        return stringBuilder.toString().trim()
    }

    private fun calculateProductsTotal(cartItems: List<CartItem>): Double {
        var total = 0.0
        for (item in cartItems) {
            total += item.price * item.quantity
        }
        return total
    }

    private fun validateInputs(): Boolean {
        return binding.deliveryDetails.text.isNotEmpty() &&
                (binding.paymentMethodGroup.checkedRadioButtonId != -1)
    }

    private fun navigateToConfirmationPage() {
        val intent = Intent(this, OrderConfirmationActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun onPaymentMethodSelected(view: View) {
        val selectedRadioButton = view as RadioButton
        if (selectedRadioButton.id == R.id.paymentCard) {
            // Open CardInfoActivity for card details
            val intent = Intent(this, CardInfoActivity::class.java)
            startActivity(intent)
        }
    }
}*/


package com.example.glovoapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.glovoapp.databinding.ActivityCheckoutBinding
import com.example.glovoapp.models.CartItem
import com.example.glovoapp.repository.CartRepository

class CheckoutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheckoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            finish() // Close the activity and go back
        }

        val savedAddress = loadAddressFromPreferences()
        if (!savedAddress.isNullOrEmpty()) {
            binding.deliveryDetails.text = savedAddress
        } else {
            binding.deliveryDetails.text = "No delivery address set"
        }

        binding.deliveryDetails.setOnClickListener {
            val intent = Intent(this, AddDeliveryAddressActivity::class.java)
            startActivity(intent) // Allow the user to change the address
        }

        displayOrderDetails()

        // Handle Confirm Order button click
        binding.confirmOrderButton.setOnClickListener {
            if (validateInputs()) {
                navigateToConfirmationPage()
            } else {
                Toast.makeText(this, "Please complete all fields before confirming the order.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadAddressFromPreferences(): String? {
        val sharedPreferences = getSharedPreferences("AppPreferences", MODE_PRIVATE)
        return sharedPreferences.getString("DELIVERY_ADDRESS", null)
    }

    private fun displayOrderDetails() {
        val cartItems = CartRepository.getCartItems()
        val deliveryCharge = 2.99
        val serviceCharge = 1.00

        val orderDetailsText = buildOrderDetailsText(cartItems)
        binding.orderDetails.text = orderDetailsText

        val productsTotal = calculateProductsTotal(cartItems)
        binding.productsPrice.text = "$${String.format("%.2f", productsTotal)}"

        binding.deliveryPrice.text = "$${String.format("%.2f", deliveryCharge)}"
        binding.servicePrice.text = "$${String.format("%.2f", serviceCharge)}"

        val totalPrice = productsTotal + deliveryCharge + serviceCharge
        binding.totalPrice.text = "$${String.format("%.2f", totalPrice)}"
    }

    private fun buildOrderDetailsText(cartItems: List<CartItem>): String {
        val stringBuilder = StringBuilder()
        for (item in cartItems) {
            stringBuilder.append("${item.name} x${item.quantity} - $${String.format("%.2f", item.price * item.quantity)}\n")
        }
        return stringBuilder.toString().trim()
    }

    private fun calculateProductsTotal(cartItems: List<CartItem>): Double {
        var total = 0.0
        for (item in cartItems) {
            total += item.price * item.quantity
        }
        return total
    }

    private fun validateInputs(): Boolean {
        return binding.deliveryDetails.text.isNotEmpty() &&
                (binding.paymentMethodGroup.checkedRadioButtonId != -1)
    }

    private fun navigateToConfirmationPage() {
        val intent = Intent(this, OrderConfirmationActivity::class.java)
        startActivity(intent)
        finish()
    }
    fun onPaymentMethodSelected(view: View) {
        val selectedRadioButton = view as RadioButton
        if (selectedRadioButton.id == R.id.paymentCard) {
            // Open CardInfoActivity for card details
            val intent = Intent(this, CardInfoActivity::class.java)
            startActivity(intent)
        }
    }
}


