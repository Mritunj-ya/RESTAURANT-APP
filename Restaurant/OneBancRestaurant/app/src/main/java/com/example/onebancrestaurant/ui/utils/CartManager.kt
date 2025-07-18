package com.example.onebancrestaurant.ui.utils

import com.example.onebancrestaurant.ui.models.Dish

object CartManager {
    private val cartItems = mutableListOf<Dish>()

    fun addToCart(dish: Dish) {
        val existing = cartItems.find { it.id == dish.id }
        if (existing != null) {
            existing.quantity += dish.quantity
        } else {
            cartItems.add(dish)
        }
    }

    fun getCartItems(): List<Dish> = cartItems

    fun getNetTotal():  Double=
        cartItems.sumOf { it.price * it.quantity }.toDouble()

    fun clearCart() = cartItems.clear()
    fun removeFromCart(dishId: String) {
        cartItems.removeAll { it.id == dishId }
    }

    fun updateQuantity(dishId: String, newQty: Int) {
        cartItems.find { it.id == dishId }?.quantity = newQty
    }

}
