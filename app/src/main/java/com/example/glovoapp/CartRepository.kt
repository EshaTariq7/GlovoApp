/*package com.example.glovoapp.repository

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.util.Log
import com.example.glovoapp.database.CartDatabaseHelper
import com.example.glovoapp.models.CartItem

class CartRepository(context: Context) {
    private val dbHelper = CartDatabaseHelper(context)
    fun addItem(item: CartItem) {
        val db = dbHelper.writableDatabase
        val existingItem = getItemByName(item.name)

        if (existingItem != null) {
            // Update quantity if item already exists
            val updatedQuantity = existingItem.quantity + item.quantity
            val values = ContentValues().apply {
                put(CartDatabaseHelper.COLUMN_QUANTITY, updatedQuantity)
            }
            val rowsAffected = db.update(
                CartDatabaseHelper.TABLE_NAME,
                values,
                "${CartDatabaseHelper.COLUMN_NAME} = ?",
                arrayOf(item.name)
            )
            Log.d("CartRepository", "Rows affected: $rowsAffected")
        } else {
            // Insert new item
            val values = ContentValues().apply {
                put(CartDatabaseHelper.COLUMN_NAME, item.name)
                put(CartDatabaseHelper.COLUMN_DESCRIPTION, item.description)
                put(CartDatabaseHelper.COLUMN_PRICE, item.price)
                put(CartDatabaseHelper.COLUMN_QUANTITY, item.quantity)
                put(CartDatabaseHelper.COLUMN_IMAGE, item.imageResId)
            }
            val result = db.insert(CartDatabaseHelper.TABLE_NAME, null, values)
            if (result == -1L) {
                Log.e("CartRepository", "Failed to add item: $item")
            } else {
                Log.d("CartRepository", "Item added with ID: $result")
            }
        }

    }




    fun removeItem(itemName: String) {
        val db = dbHelper.writableDatabase
        db.delete(
            CartDatabaseHelper.TABLE_NAME,
            "${CartDatabaseHelper.COLUMN_NAME} = ?",
            arrayOf(itemName)
        )
        db.close()
    }

    fun getCartItems(): List<CartItem> {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            CartDatabaseHelper.TABLE_NAME,
            null, // Select all columns
            null, // No WHERE clause
            null,
            null,
            null,
            null
        )

        val cartItems = mutableListOf<CartItem>()
        if (cursor.moveToFirst()) {
            do {
                val cartItem = cursorToCartItem(cursor)
                Log.d("CartRepository", "Retrieved item: $cartItem")
                cartItems.add(cartItem)
            } while (cursor.moveToNext())
        } else {
            Log.d("CartRepository", "No items found in cart")
        }

        cursor.close()
        db.close()
        return cartItems
    }


    private fun getItemByName(name: String): CartItem? {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            CartDatabaseHelper.TABLE_NAME,
            null,
            "${CartDatabaseHelper.COLUMN_NAME} = ?",
            arrayOf(name),
            null,
            null,
            null
        )
        var item: CartItem? = null
        if (cursor.moveToFirst()) {
            item = cursorToCartItem(cursor)
        }
        cursor.close()
        db.close()
        return item
    }

    private fun cursorToCartItem(cursor: Cursor): CartItem {
        return CartItem(
            name = cursor.getString(cursor.getColumnIndexOrThrow(CartDatabaseHelper.COLUMN_NAME)),
            description = cursor.getString(cursor.getColumnIndexOrThrow(CartDatabaseHelper.COLUMN_DESCRIPTION)),
            price = cursor.getDouble(cursor.getColumnIndexOrThrow(CartDatabaseHelper.COLUMN_PRICE)),
            quantity = cursor.getInt(cursor.getColumnIndexOrThrow(CartDatabaseHelper.COLUMN_QUANTITY)),
            imageResId = cursor.getInt(cursor.getColumnIndexOrThrow(CartDatabaseHelper.COLUMN_IMAGE))
        )
    }
    fun getTotalAmount(): Double {
        val db = dbHelper.readableDatabase
        var totalAmount = 0.0

        val cursor = db.rawQuery(
            "SELECT SUM(${CartDatabaseHelper.COLUMN_PRICE} * ${CartDatabaseHelper.COLUMN_QUANTITY}) AS total FROM ${CartDatabaseHelper.TABLE_NAME}",
            null
        )

        if (cursor.moveToFirst()) {
            totalAmount = cursor.getDouble(cursor.getColumnIndexOrThrow("total"))
        }

        cursor.close()
        db.close()
        return totalAmount
    }

}*/



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