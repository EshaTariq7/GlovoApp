/*package com.example.glovoapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.glovoapp.R
import com.example.glovoapp.models.CartItem
import com.example.glovoapp.repository.CartRepository


class CartAdapter(
    private val context: Context,
    private val cartItems: MutableList<CartItem>,
    private val onItemUpdated: () -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    private val cartRepository = CartRepository(context)

    inner class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName: TextView = itemView.findViewById(R.id.item_name)
        val itemPrice: TextView = itemView.findViewById(R.id.item_price)
        val itemQuantity: TextView = itemView.findViewById(R.id.item_quantity)
        val minusButton: ImageButton = itemView.findViewById(R.id.minus_button)
        val plusButton: ImageButton = itemView.findViewById(R.id.plus_button)
        val removeButton: ImageButton = itemView.findViewById(R.id.remove_button)
        val itemImage: ImageView = itemView.findViewById(R.id.item_image) // Add this line
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cart_item_layout, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cartItem = cartItems[position]
        holder.itemName.text = cartItem.name
        holder.itemPrice.text = "$${String.format("%.2f", cartItem.price)}"
        holder.itemQuantity.text = cartItem.quantity.toString()

        // Update the item image using the imageResId
        holder.itemImage.setImageResource(cartItem.imageResId)

        holder.plusButton.setOnClickListener {
            cartItem.quantity++
            cartRepository.addItem(cartItem) // Update in the database
            notifyItemChanged(position)
            onItemUpdated()
        }

        holder.minusButton.setOnClickListener {
            if (cartItem.quantity > 1) {
                cartItem.quantity--
                cartRepository.addItem(cartItem) // Update in the database
                notifyItemChanged(position)
                onItemUpdated()
            }
        }

        holder.removeButton.setOnClickListener {
            cartRepository.removeItem(cartItem.name) // Remove from the database
            cartItems.removeAt(position)
            notifyItemRemoved(position)
            onItemUpdated()
        }
    }

    override fun getItemCount() = cartItems.size
}*/



package com.example.glovoapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.glovoapp.R
import com.example.glovoapp.models.CartItem
import com.example.glovoapp.repository.CartRepository

class CartAdapter(
    private val cartItems: MutableList<CartItem>,
    private val onItemUpdated: () -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName: TextView = itemView.findViewById(R.id.item_name)
        val itemPrice: TextView = itemView.findViewById(R.id.item_price)
        val itemQuantity: TextView = itemView.findViewById(R.id.item_quantity)
        val minusButton: ImageButton = itemView.findViewById(R.id.minus_button)
        val plusButton: ImageButton = itemView.findViewById(R.id.plus_button)
        val removeButton: ImageButton = itemView.findViewById(R.id.remove_button)
        val itemImage: ImageView = itemView.findViewById(R.id.item_image) // Add this line
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cart_item_layout, parent, false)
        return CartViewHolder(view)
    }
    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cartItem = cartItems[position]
        holder.itemName.text = cartItem.name
        holder.itemPrice.text = "$${String.format("%.2f", cartItem.price)}"
        holder.itemQuantity.text = cartItem.quantity.toString()

        // Update the item image using the imageResId
        holder.itemImage.setImageResource(cartItem.imageResId)

        holder.plusButton.setOnClickListener {
            cartItem.quantity++
            notifyItemChanged(position)
            onItemUpdated()
        }

        holder.minusButton.setOnClickListener {
            if (cartItem.quantity > 1) {
                cartItem.quantity--
                notifyItemChanged(position)
                onItemUpdated()
            }
        }

        holder.removeButton.setOnClickListener {
            CartRepository.removeItem(cartItem)
            cartItems.removeAt(position)
            notifyItemRemoved(position)
            onItemUpdated()
        }
    }

    override fun getItemCount() = cartItems.size
}




