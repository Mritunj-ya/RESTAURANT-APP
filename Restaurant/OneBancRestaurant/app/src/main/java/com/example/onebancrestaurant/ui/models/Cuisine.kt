package com.example.onebancrestaurant.ui.models

import com.example.onebancrestaurant.ui.models.Dish

data class Cuisine(
    val cuisineId: String,
    val cuisineName: String,
    val cuisineImageUrl: String,
    val items: List<Dish>
)
