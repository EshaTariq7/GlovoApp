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

class CondisActivity : AppCompatActivity() {

    private val menuItems = listOf(
        MenuItem("Greek Salad", "A refreshing salad with feta cheese, olives, and tomatoes.", 8.99, R.drawable.c1),
        MenuItem("Tropical Fruit Bowl", "A mix of tropical fruits including mango, pineapple, and banana.", 6.49, R.drawable.c2),
        MenuItem("Berry Smoothie", "A delicious blend of strawberries, blueberries, and yogurt.", 5.99, R.drawable.c3),
        MenuItem("Caesar Salad", "Crisp romaine lettuce with Caesar dressing, croutons, and Parmesan.", 9.49, R.drawable.c4),
        MenuItem("Mango Lassi", "A creamy yogurt drink flavored with fresh mango.", 3.99, R.drawable.c5),
        MenuItem("Quinoa Salad", "Nutritious quinoa mixed with vegetables and a lemon dressing.", 7.49, R.drawable.c6),
        MenuItem("Avocado Toast", "Toasted bread topped with smashed avocado and spices.", 5.49, R.drawable.c7),
        MenuItem("Chia Seed Pudding", "Healthy chia seeds soaked in almond milk with vanilla.", 4.99, R.drawable.c8),
        MenuItem("Mixed Nuts", "A variety of nuts for a healthy snack.", 3.49, R.drawable.c9),
        MenuItem("Fresh Salad", "Healthy mixed salad with dressing.", 7.99, R.drawable.c1),
        MenuItem("Fruit Bowl", "Seasonal fruits served fresh.", 5.49, R.drawable.c2),
        MenuItem("Smoothie", "Refreshing fruit smoothie.", 4.99, R.drawable.c3)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_condis)

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

class CondisActivity : AppCompatActivity() {

    private val menuItems = listOf(
        MenuItem("Greek Salad", "A refreshing salad with feta cheese, olives, and tomatoes.", 8.99, R.drawable.c1),
        MenuItem("Tropical Fruit Bowl", "A mix of tropical fruits including mango, pineapple, and banana.", 6.49, R.drawable.c2),
        MenuItem("Berry Smoothie", "A delicious blend of strawberries, blueberries, and yogurt.", 5.99, R.drawable.c3),
        MenuItem("Caesar Salad", "Crisp romaine lettuce with Caesar dressing, croutons, and Parmesan.", 9.49, R.drawable.c4),
        MenuItem("Mango Lassi", "A creamy yogurt drink flavored with fresh mango.", 3.99, R.drawable.c5),
        MenuItem("Quinoa Salad", "Nutritious quinoa mixed with vegetables and a lemon dressing.", 7.49, R.drawable.c6),
        MenuItem("Avocado Toast", "Toasted bread topped with smashed avocado and spices.", 5.49, R.drawable.c7),
        MenuItem("Chia Seed Pudding", "Healthy chia seeds soaked in almond milk with vanilla.", 4.99, R.drawable.c8),
        MenuItem("Mixed Nuts", "A variety of nuts for a healthy snack.", 3.49, R.drawable.c9),
        MenuItem("Fresh Salad", "Healthy mixed salad with dressing.", 7.99, R.drawable.c1),
        MenuItem("Fruit Bowl", "Seasonal fruits served fresh.", 5.49, R.drawable.c2),
        MenuItem("Smoothie", "Refreshing fruit smoothie.", 4.99, R.drawable.c3)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_condis)

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

