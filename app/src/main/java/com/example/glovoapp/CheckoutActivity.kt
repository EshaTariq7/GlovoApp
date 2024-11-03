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

        displayOrderDetails()
       // setupConfirmOrderButton()
    }

    private fun displayOrderDetails() {
        val cartItems = CartRepository.getCartItems()
        val deliveryCharge = 2.99 // You can replace this with your logic
        val serviceCharge = 1.00 // You can replace this with your logic

        // Displaying the order details
        val orderDetailsText = buildOrderDetailsText(cartItems)
        binding.orderDetails.text = orderDetailsText

        // Calculating the products total
        val productsTotal = calculateProductsTotal(cartItems)
        binding.productsPrice.text = "$${String.format("%.2f", productsTotal)}"

        // Display delivery and service prices
        binding.deliveryPrice.text = "$${String.format("%.2f", deliveryCharge)}"
        binding.servicePrice.text = "$${String.format("%.2f", serviceCharge)}"

        // Calculating the total price
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
    // This function will be called when a payment method is selected
    fun onPaymentMethodSelected(view: View) {
        val selectedRadioButton = view as RadioButton
        if (selectedRadioButton.id == R.id.paymentCard) {
            // Open CardInfoActivity for card details
            val intent = Intent(this, CardInfoActivity::class.java)
            startActivity(intent)
        }
    }

   /* private fun setupConfirmOrderButton() {
        binding.confirmOrderButton.setOnClickListener {
            // Handle order confirmation logic here, e.g., save order to database or display a success message.
            Toast.makeText(this, "Order confirmed!", Toast.LENGTH_SHORT).show()

            // Optionally, clear the cart after confirming the order
            CartRepository.clearCart()

            // Finish the activity and go back to the main page or a confirmation page
            finish()
        }
    }*/

}
