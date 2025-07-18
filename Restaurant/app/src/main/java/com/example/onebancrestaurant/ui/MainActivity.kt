package com.example.onebancrestaurant.ui

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.onebancrestaurant.R
import com.example.onebancrestaurant.ui.adapters.CuisineAdapter
import com.example.onebancrestaurant.ui.adapters.DishAdapter
import com.example.onebancrestaurant.ui.models.Cuisine
import com.example.onebancrestaurant.ui.models.Dish
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var cuisineRecycler: RecyclerView
    private lateinit var topDishesRecycler: RecyclerView
    private lateinit var btnCart: Button
    private lateinit var btnLanguage: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // View bindings
        cuisineRecycler = findViewById(R.id.recyclerCuisine)
        topDishesRecycler = findViewById(R.id.recyclerTopDishes)
        btnCart = findViewById(R.id.btnCart)
        btnLanguage = findViewById(R.id.btnLanguage)

        // Set layout managers
        cuisineRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        topDishesRecycler.layoutManager = LinearLayoutManager(this)

        // Language switcher button
        btnLanguage.setOnClickListener {
            val currentLang = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                resources.configuration.locales.get(0).language
            } else {
                @Suppress("DEPRECATION")
                resources.configuration.locale.language
            }

            val newLang = if (currentLang == "en") "hi" else "en"
            val locale = Locale(newLang)
            Locale.setDefault(locale)

            val config = Configuration()
            config.setLocale(locale)
            @Suppress("DEPRECATION")
            resources.updateConfiguration(config, resources.displayMetrics)

            // Restart activity
            val refresh = Intent(this, MainActivity::class.java)
            finish()
            startActivity(refresh)
        }

        // Cart button logic
        btnCart.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }

        // Load data
        loadDummyData()
    }

    private fun loadDummyData() {
        val cuisineList = listOf(
            Cuisine("1", "Chinese", "", emptyList()),
            Cuisine("2", "Italian", "", emptyList()),
            Cuisine("3", "North Indian", "", emptyList()),
            Cuisine("4", "South Indian", "", emptyList()),
            Cuisine("5", "Mexican", "", emptyList())
        )
        cuisineRecycler.adapter = CuisineAdapter(this, cuisineList)

        val topDishes = listOf(
            Dish("1", "Chow Mein", R.drawable.chow_mein, 120, 4.5, 0),
            Dish("2", "Manchurian", R.drawable.manchurian, 140, 4.3, 0),
            Dish("3", "Spring Roll", R.drawable.spring_roll, 100, 4.2, 0)
        )
        topDishesRecycler.adapter = DishAdapter(topDishes)
    }
}
