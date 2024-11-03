package com.example.glovoapp

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.example.glovoapp.databinding.ActivityStoresBinding

class StoresActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStoresBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize binding
        binding = ActivityStoresBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up SearchView listener if needed
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Handle query submission
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Handle text changes
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

        // Find layouts for all restaurants

        val mcdonaldsLayout: LinearLayout = binding.storesGrid.findViewById(R.id.mcdonalds_layout)
        val kfcLayout: LinearLayout = binding.storesGrid.findViewById(R.id.kfc_layout)
        val vicioLayout: LinearLayout = binding.storesGrid.findViewById(R.id.vicio_layout)
        val fiveGuysLayout: LinearLayout = binding.storesGrid.findViewById(R.id.five_guys_layout)
        val condiesLayout: LinearLayout = binding.storesGrid.findViewById(R.id.condis_layout)
        val honestGreensLayout: LinearLayout = binding.storesGrid.findViewById(R.id.honest_green_layout)

        // Set click listeners
        mcdonaldsLayout.setOnClickListener {
            // Navigate to McDonald's menu activity
            val intent = Intent(this, McdonaldActivity::class.java)
            startActivity(intent)
        }

        // Set click listeners for each restaurant
        kfcLayout.setOnClickListener {
            val intent = Intent(this, KfcActivity::class.java) // Replace with KFC activity if available
            startActivity(intent)
        }

        vicioLayout.setOnClickListener {
            val intent = Intent(this, VicioActivity::class.java) // Replace with Vicio activity
            startActivity(intent)
        }

        fiveGuysLayout.setOnClickListener {
            val intent = Intent(this, FiveGuysActivity::class.java) // Replace with Five Guys activity
            startActivity(intent)
        }

        condiesLayout.setOnClickListener {
            val intent = Intent(this, CondisActivity::class.java) // Replace with Condis activity
            startActivity(intent)
        }

        honestGreensLayout.setOnClickListener {
            val intent = Intent(this, HonestGreensActivity::class.java) // Replace with Honest Greens activity
            startActivity(intent)
        }
    }
}
