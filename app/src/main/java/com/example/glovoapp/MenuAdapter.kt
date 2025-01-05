

package com.example.glovoapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.glovoapp.R
import com.example.glovoapp.models.MenuItem
import com.example.glovoapp.repository.CartRepository
import com.example.glovoapp.models.CartItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

/*class MenuAdapter(
    private val context: Context,
    private val menuItems: List<MenuItem>,
    private val onAddToCart: (MenuItem) -> Unit
) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.menu_item_layout, parent, false)
        return MenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val menuItem = menuItems[position]
        holder.bind(menuItem)
    }

    override fun getItemCount(): Int = menuItems.size

    inner class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemName: TextView = itemView.findViewById(R.id.item_name)
        private val itemDescription: TextView = itemView.findViewById(R.id.item_description)
        private val itemPrice: TextView = itemView.findViewById(R.id.item_price)
        private val itemImage: ImageView = itemView.findViewById(R.id.item_image)
        private val addToCartButton: Button = itemView.findViewById(R.id.add_to_cart_button)

        fun bind(menuItem: MenuItem) {
            itemName.text = menuItem.name
            itemDescription.text = menuItem.description
            itemPrice.text = "Price: $${menuItem.price}"
            itemImage.setImageResource(menuItem.imageResId)



            addToCartButton.setOnClickListener {
                val cartRepository = CartRepository(itemView.context)
                val cartItem = CartItem(
                    name = menuItem.name,
                    description = menuItem.description,
                    price = menuItem.price,
                    imageResId = menuItem.imageResId
                )
                cartRepository.addItem(cartItem) // Add to database
                Toast.makeText(itemView.context, "${menuItem.name} added to cart", Toast.LENGTH_SHORT).show()
                onAddToCart(menuItem) // Notify activity
            }



        }
    }
}*/


class MenuAdapter(
    private val menuItems: List<MenuItem>,
    private val onAddToCart: (MenuItem) -> Unit
) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.menu_item_layout, parent, false)
        return MenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val menuItem = menuItems[position]
        holder.bind(menuItem)
    }

    override fun getItemCount(): Int = menuItems.size

    inner class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemName: TextView = itemView.findViewById(R.id.item_name)
        private val itemDescription: TextView = itemView.findViewById(R.id.item_description)
        private val itemPrice: TextView = itemView.findViewById(R.id.item_price)
        private val itemImage: ImageView = itemView.findViewById(R.id.item_image)
        private val addToCartButton: Button = itemView.findViewById(R.id.add_to_cart_button)

        fun bind(menuItem: MenuItem) {
            itemName.text = menuItem.name
            itemDescription.text = menuItem.description
            itemPrice.text = "Price: $${menuItem.price}"
            itemImage.setImageResource(menuItem.imageResId)

            addToCartButton.setOnClickListener {
                onAddToCart(menuItem)
            }
        }
    }
}
