package com.example.onebancrestaurant.utils

import android.content.Context
import com.example.onebancrestaurant.R

object LanguageUtil {
    fun getLocalizedCuisineName(context: Context, name: String): String {
        return when (name) {
            "Chinese" -> context.getString(R.string.cuisine_chinese)
            "Italian" -> context.getString(R.string.cuisine_italian)
            // Add others if needed
            else -> name
        }
    }

    fun getLocalizedDishName(context: Context, name: String): String {
        return when (name) {
            "Chow Mein" -> context.getString(R.string.dish_chow_mein)
            "Manchurian" -> context.getString(R.string.dish_manchurian)
            "Spring Roll" -> context.getString(R.string.dish_spring_roll)
            else -> name
        }
    }
}
