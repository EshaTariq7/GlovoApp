package com.example.glovoapp.repository

import android.util.Log
import com.example.glovoapp.models.CartItem

object CartRepository {
    private val cartItems = mutableListOf<CartItem>()

    fun addItem(item: CartItem) {
        val existingItem = cartItems.find { it.name == item.name }
        if (existingItem != null) {
            existingItem.quantity++
            Log.d("CartRepository", "Increased quantity of ${item.name} to ${existingItem.quantity}")
        } else {
            cartItems.add(item)
            Log.d("CartRepository", "Added ${item.name} to cart")
        }
        Log.d("CartRepository", "Current cart items: $cartItems") // Log current items
    }
    fun removeItem(item: CartItem) {
        cartItems.remove(item)
    }

    fun increaseQuantity(item: CartItem) {
        item.quantity++
    }

    fun decreaseQuantity(item: CartItem) {
        if (item.quantity > 1) {
            item.quantity--
        }
    }

    fun getCartItems(): List<CartItem> = cartItems

    fun getTotalAmount(): Double = cartItems.sumOf { it.price * it.quantity }
}
