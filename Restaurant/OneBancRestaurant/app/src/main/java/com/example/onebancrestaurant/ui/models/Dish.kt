package com.example.onebancrestaurant.ui.models

data class Dish(
    val id: String,
    val name: String,
    val imageRes: Int,
    val price: Int,
    val rating: Double,
    var quantity: Int = 0
)
