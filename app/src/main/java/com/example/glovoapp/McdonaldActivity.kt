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

class McdonaldActivity : AppCompatActivity() {

    private val menuItems = listOf(
        MenuItem("Big Mac", "A delicious burger with two patties and special sauce.", 5.99, R.drawable.b1),
        MenuItem("Crispy Mcmenu", "Crispy golden fries, perfect as a side.", 2.99, R.drawable.b2),
        MenuItem("Friend Box Menu", "Delicious chicken battered with sauce and cheese.", 6.99, R.drawable.b3),
        MenuItem("Sharebox Nuggets", "A box of crispy nuggets.", 7.99, R.drawable.b4),
        MenuItem("McFlurry Oreo", "Creamy dessert with Oreo pieces.", 2.99, R.drawable.b5),
        MenuItem("Chicken Wings", "Deliciously fried chicken wings with sauce.", 7.49, R.drawable.b2),
        MenuItem("Mac & Cheese", "Creamy macaroni and cheese.", 3.99, R.drawable.h2),
        MenuItem("Chocolate Cake", "Rich chocolate cake served with icing.", 4.49, R.drawable.b7)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mcdonald)

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

            // Open the cart page after adding to cart
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

class McdonaldActivity : AppCompatActivity() {

    private val menuItems = listOf(
        MenuItem("Big Mac", "A delicious burger with two patties and special sauce.", 5.99, R.drawable.b1),
        MenuItem("Crispy Mcmenu", "Crispy golden fries, perfect as a side.", 2.99, R.drawable.b2) ,
        MenuItem("Friend Box Menu", "Delicious chicken battered with sauce and cheese.", 6.99, R.drawable.b3),
        MenuItem("Sharebox Nuggets", "A box of crispy nuggets.", 7.99, R.drawable.b4),
        MenuItem("McFlurry Oreo", "Creamy dessert with Oreo pieces.", 2.99, R.drawable.b5),
        MenuItem("Chicken Wings", "Deliciously fried chicken wings with sauce.", 7.49, R.drawable.b2),
        MenuItem("Mac & Cheese", "Creamy macaroni and cheese.", 3.99, R.drawable.h2),
        MenuItem("Chocolate Cake", "Rich chocolate cake served with icing.", 4.49, R.drawable.b7)

    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mcdonald)

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

            // Open the cart page after adding to cart
            startActivity(Intent(this, CartActivity::class.java))
        }
    }
}


