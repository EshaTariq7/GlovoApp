package com.example.glovoapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.glovoapp.models.MenuItem
import com.example.glovoapp.adapters.MenuAdapter
import com.example.glovoapp.models.CartItem
import com.example.glovoapp.repository.CartRepository

class FiveGuysActivity : AppCompatActivity() {

    private val menuItems = listOf(
        MenuItem("Bacon Cheeseburger", "Juicy burger with crispy bacon.", 9.99, R.drawable.f1),
        MenuItem("French Fries", "Crispy fries, made fresh daily.", 3.99, R.drawable.f2),
        MenuItem("Milkshake", "Rich and creamy milkshake.", 4.99, R.drawable.f3),
        MenuItem("Veggie Sandwich", "Fresh veggies in a toasted sandwich.", 6.49, R.drawable.f4),
        MenuItem("Hot Dog", "Grilled hot dog served with mustard.", 5.99, R.drawable.f5),
        MenuItem("Grilled Cheese", "Classic grilled cheese sandwich.", 4.49, R.drawable.f6),
        MenuItem("Chocolate Chip Cookies", "Freshly baked cookies with chocolate chips.", 2.99, R.drawable.f7),
        MenuItem("Onion Rings", "Crispy onion rings served with dipping sauce.", 3.49, R.drawable.b3),
        MenuItem("Soda", "Refreshing soft drink.", 1.99, R.drawable.f2),
        MenuItem("Garden Salad", "Mixed greens with a choice of dressing.", 5.49, R.drawable.f1),
        MenuItem("Bacon Cheeseburger", "Juicy burger with crispy bacon.", 9.99, R.drawable.b1),
        MenuItem("French Fries", "Crispy fries, made fresh daily.", 3.99, R.drawable.f2)


        // Add more items as needed
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_five_guys)

        val recyclerView = findViewById<RecyclerView>(R.id.menuRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MenuAdapter(menuItems) { menuItem ->
            val item = CartItem(
                name = menuItem.name,
                description = menuItem.description,
                price = menuItem.price,
                imageResId = menuItem.imageResId
            )
            CartRepository.addItem(item)
            Toast.makeText(this, "${menuItem.name} added to cart", Toast.LENGTH_SHORT).show()

            // Optionally open the cart page after adding to cart
            startActivity(Intent(this, CartActivity::class.java))
        }
    }
}
