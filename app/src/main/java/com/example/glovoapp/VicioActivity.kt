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

class VicioActivity : AppCompatActivity() {

    private val menuItems = listOf(
        MenuItem("Pizza Margherita", "Classic pizza with fresh mozzarella.", 10.99, R.drawable.k1),
        MenuItem("Pasta Carbonara", "Delicious pasta with creamy sauce.", 12.99, R.drawable.b2),
        MenuItem("Tiramisu", "Traditional Italian dessert with coffee.", 5.99, R.drawable.f3),
        MenuItem("Bruschetta", "Grilled bread topped with diced tomatoes and basil.", 6.49, R.drawable.b4),
        MenuItem("Fettuccine Alfredo", "Creamy fettuccine pasta with Parmesan cheese.", 11.99, R.drawable.b5),
        MenuItem("Caprese Salad", "Fresh mozzarella, tomatoes, and basil drizzled with balsamic.", 7.99, R.drawable.b6),
        MenuItem("Lasagna", "Layers of pasta, meat, and cheese baked to perfection.", 13.49, R.drawable.b7),
        MenuItem("Risotto ai Funghi", "Creamy risotto with mushrooms and herbs.", 12.49, R.drawable.k2),
        MenuItem("Cannoli", "Italian pastry filled with sweet ricotta cheese.", 4.99, R.drawable.f1),
        MenuItem("Panna Cotta", "Delicate dessert made with cream and vanilla.", 5.49, R.drawable.b7),
        MenuItem("Pizza Margherita", "Classic pizza with fresh mozzarella.", 10.99, R.drawable.k1),
        MenuItem("Pasta Carbonara", "Delicious pasta with creamy sauce.", 12.99, R.drawable.b2),
        MenuItem("Tiramisu", "Traditional Italian dessert.", 5.99, R.drawable.f3),
        MenuItem("Tiramisu", "Traditional Italian dessert.", 5.99, R.drawable.f2)
        // Add more items as needed
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vicio)

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

class VicioActivity : AppCompatActivity() {

    private val menuItems = listOf(
        MenuItem("Pizza Margherita", "Classic pizza with fresh mozzarella.", 10.99, R.drawable.k1),
        MenuItem("Pasta Carbonara", "Delicious pasta with creamy sauce.", 12.99, R.drawable.b2),
        MenuItem("Tiramisu", "Traditional Italian dessert with coffee.", 5.99, R.drawable.f3),
        MenuItem("Bruschetta", "Grilled bread topped with diced tomatoes and basil.", 6.49, R.drawable.b4),
        MenuItem("Fettuccine Alfredo", "Creamy fettuccine pasta with Parmesan cheese.", 11.99, R.drawable.b5),
        MenuItem("Caprese Salad", "Fresh mozzarella, tomatoes, and basil drizzled with balsamic.", 7.99, R.drawable.b6),
        MenuItem("Lasagna", "Layers of pasta, meat, and cheese baked to perfection.", 13.49, R.drawable.b7),
        MenuItem("Risotto ai Funghi", "Creamy risotto with mushrooms and herbs.", 12.49, R.drawable.k2),
        MenuItem("Cannoli", "Italian pastry filled with sweet ricotta cheese.", 4.99, R.drawable.f1),
        MenuItem("Panna Cotta", "Delicate dessert made with cream and vanilla.", 5.49, R.drawable.b7),
        MenuItem("Pizza Margherita", "Classic pizza with fresh mozzarella.", 10.99, R.drawable.k1),
        MenuItem("Pasta Carbonara", "Delicious pasta with creamy sauce.", 12.99, R.drawable.b2),
        MenuItem("Tiramisu", "Traditional Italian dessert.", 5.99, R.drawable.f3),
        MenuItem("Tiramisu", "Traditional Italian dessert.", 5.99, R.drawable.f2)
        // Add more items as needed
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vicio)

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
