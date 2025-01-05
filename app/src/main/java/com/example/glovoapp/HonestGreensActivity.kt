/*package com.example.glovoapp

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

class HonestGreensActivity : AppCompatActivity() {

    private val menuItems = listOf(
        MenuItem("Quinoa Bowl", "Nutritious quinoa with vegetables.", 8.99, R.drawable.h1),
        MenuItem("Veggie Wrap", "Delicious wrap filled with fresh veggies.", 6.49, R.drawable.h2),
        MenuItem("Green Smoothie", "Healthy smoothie with greens.", 5.99, R.drawable.h3)
        // Add more items as needed
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_honest_greens)

        val recyclerView = findViewById<RecyclerView>(R.id.menuRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MenuAdapter(this, menuItems) { menuItem ->
            val item = CartItem(
                name = menuItem.name,
                description = menuItem.description,
                price = menuItem.price,
                imageResId = menuItem.imageResId
            )
            CartRepository(this).addItem(item)
            Toast.makeText(this, "${menuItem.name} added to cart", Toast.LENGTH_SHORT).show()

            // Optionally open the cart page after adding to cart
            startActivity(Intent(this, CartActivity::class.java))
        }
    }
}*/

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

class HonestGreensActivity : AppCompatActivity() {

    private val menuItems = listOf(
        MenuItem("Quinoa Bowl", "Nutritious quinoa with vegetables.", 8.99, R.drawable.h1),
        MenuItem("Veggie Wrap", "Delicious wrap filled with fresh veggies.", 6.49, R.drawable.h2),
        MenuItem("Green Smoothie", "Healthy smoothie with greens.", 5.99, R.drawable.h3)
        // Add more items as needed
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_honest_greens)

        val recyclerView = findViewById<RecyclerView>(R.id.menuRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MenuAdapter(menuItems) { menuItem ->
            val item = CartItem(
                name = menuItem.name,
                description = menuItem.description,
                price = menuItem.price,
                imageResId = menuItem.imageResId // Pass the image resource ID
            )
            CartRepository.addItem(item)
            Toast.makeText(this, "${menuItem.name} added to cart", Toast.LENGTH_SHORT).show()

            // Optionally open the cart page after adding to cart
            startActivity(Intent(this, CartActivity::class.java))
        }
    }
}
