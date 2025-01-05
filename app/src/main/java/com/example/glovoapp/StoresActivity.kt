package com.example.glovoapp

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.example.glovoapp.databinding.ActivityStoresBinding

class StoresActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStoresBinding
    private lateinit var restaurantLayouts: Map<String, Class<out AppCompatActivity>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize binding
        binding = ActivityStoresBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize restaurant layouts with their corresponding activities
        restaurantLayouts = mapOf(
            "McDonald's" to McdonaldActivity::class.java,
            "KFC" to KfcActivity::class.java,
            "Vicio" to VicioActivity::class.java,
            "Five Guys" to FiveGuysActivity::class.java,
            "Condis" to CondisActivity::class.java,
            "Honest Greens" to HonestGreensActivity::class.java
        )

        // Set up SearchView functionality
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { searchAndNavigateToRestaurant(it) }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        // Navigate to Profile Activity when the profile image is clicked
        binding.profileImage.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        // Navigate to CartActivity when the cart icon is clicked
        binding.cartIcon.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }

        // Set click listeners for restaurant layouts
        restaurantLayouts.forEach { (name, activityClass) ->
            val layoutId = when (name) {
                "McDonald's" -> R.id.mcdonalds_layout
                "KFC" -> R.id.kfc_layout
                "Vicio" -> R.id.vicio_layout
                "Five Guys" -> R.id.five_guys_layout
                "Condis" -> R.id.condis_layout
                "Honest Greens" -> R.id.honest_green_layout
                else -> null
            }

            layoutId?.let { id ->
                val layout = binding.storesGrid.findViewById<LinearLayout>(id)
                layout.setOnClickListener {
                    val intent = Intent(this, activityClass)
                    startActivity(intent)
                }
            }
        }
    }

    private fun searchAndNavigateToRestaurant(query: String) {
        val normalizedQuery = query.trim().lowercase()

        // Find the restaurant by name
        val matchedRestaurant = restaurantLayouts.entries.find { it.key.lowercase().contains(normalizedQuery) }

        if (matchedRestaurant != null) {
            // Navigate to the matched restaurant's page
            val intent = Intent(this, matchedRestaurant.value)
            startActivity(intent)
        } else {
            // If no match, show a not available message
            Toast.makeText(this, "Restaurant not available", Toast.LENGTH_SHORT).show()
        }
    }
}



