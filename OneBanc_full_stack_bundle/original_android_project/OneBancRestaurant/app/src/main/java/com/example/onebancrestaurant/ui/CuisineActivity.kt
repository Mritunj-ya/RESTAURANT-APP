package com.example.onebancrestaurant.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.onebancrestaurant.R
import com.example.onebancrestaurant.ui.adapters.DishAdapter
import com.example.onebancrestaurant.ui.models.Dish

class CuisineActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cuisine)

        recyclerView = findViewById(R.id.recyclerDishes)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val cuisineName = intent.getStringExtra("cuisine_name")
        if (cuisineName == null) {
            Toast.makeText(this, "No cuisine selected", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        title = cuisineName

        val allDishes = listOf(
            // Chinese
            Dish("1", "Chow Mein", R.drawable.chow_mein, 120, 4.5),
            Dish("2", "Manchurian", R.drawable.manchurian, 140, 4.3),
            Dish("3", "Spring Roll", R.drawable.spring_roll, 100, 4.2),

            // Mexican
            Dish("4", "Tacos", R.drawable.tacos, 130, 4.4),
            Dish("5", "Burrito", R.drawable.burrito, 150, 4.1),
            Dish("6", "Quesadilla", R.drawable.quesadilla, 160, 4.0),

            // Italian
            Dish("7", "Pizza", R.drawable.pizza, 200, 4.7),
            Dish("8", "Pasta", R.drawable.pasta, 180, 4.4),
            Dish("9", "Lasagna", R.drawable.lasagna, 220, 4.6),

            // South Indian
            Dish("10", "Dosa", R.drawable.dosa, 90, 4.3),
            Dish("11", "Idli", R.drawable.idli, 60, 4.2),
            Dish("12", "Vada", R.drawable.vada, 70, 4.1),

            // North Indian
            Dish("13", "Paneer Butter Masala", R.drawable.paneer, 160, 4.5),
            Dish("14", "Chole Bhature", R.drawable.chole_bhature, 140, 4.4),
            Dish("15", "Dal Makhani", R.drawable.dal_makhani, 150, 4.3)
        )

        val cuisineDishes = when (cuisineName) {
            "Chinese" -> allDishes.filter { it.id in listOf("1", "2", "3") }
            "Mexican" -> allDishes.filter { it.id in listOf("4", "5", "6") }
            "Italian" -> allDishes.filter { it.id in listOf("7", "8", "9") }
            "South Indian" -> allDishes.filter { it.id in listOf("10", "11", "12") }
            "North Indian" -> allDishes.filter { it.id in listOf("13", "14", "15") }
            else -> emptyList()
        }

        recyclerView.adapter = DishAdapter(cuisineDishes)
    }
}
