/*package com.example.glovoapp.models

data class CartItem(
    val id: String = "", // Unique identifier for backend operations
    val name: String,
    val description: String,
    val price: Double,
    var quantity: Int = 1,
    val imageResId: Int
)*/


package com.example.glovoapp.models

data class CartItem(
    val name: String,
    val description: String,
    val price: Double,
    var quantity: Int = 1,
    val imageResId: Int // Add this property
)
