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

class KfcActivity : AppCompatActivity() {

    private val menuItems = listOf(
        MenuItem("KFC Bucket", "A bucket of crispy fried chicken.", 15.99, R.drawable.k1),
        MenuItem("Mashed Potatoes", "Creamy mashed potatoes with gravy.", 3.99, R.drawable.k2),
        MenuItem("Coleslaw", "Fresh coleslaw salad.", 2.99, R.drawable.k3),
        MenuItem("Zinger Burger", "Spicy chicken fillet burger with lettuce and mayo.", 6.49, R.drawable.k1),
        MenuItem("Chicken Sandwich", "Crispy chicken sandwich served with pickles.", 5.99, R.drawable.k1),
        MenuItem("Popcorn Chicken", "Bite-sized chicken pieces, perfectly seasoned.", 4.99, R.drawable.k3),
        MenuItem("Fries", "Golden and crispy French fries.", 2.49, R.drawable.b1),
        MenuItem("Chicken Wings", "Deliciously fried chicken wings with sauce.", 7.49, R.drawable.b2),
        MenuItem("Mac & Cheese", "Creamy macaroni and cheese.", 3.99, R.drawable.h2),
        MenuItem("Chocolate Cake", "Rich chocolate cake served with icing.", 4.49, R.drawable.b7)
        // Add more items as needed
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kfc)

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
