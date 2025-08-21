package com.example.onebancrestaurant.ui.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.onebancrestaurant.R
import com.example.onebancrestaurant.ui.models.Cuisine
import com.example.onebancrestaurant.ui.CuisineActivity
import com.example.onebancrestaurant.utils.LanguageUtil

class CuisineAdapter(
    private val context: Context,
    private val cuisines: List<Cuisine>
) : RecyclerView.Adapter<CuisineAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageCuisine: ImageView = view.findViewById(R.id.imageCuisine)
        val textCuisineName: TextView = view.findViewById(R.id.textCuisineName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cuisine, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = cuisines.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cuisine = cuisines[position]

        // Translate the cuisine name (based on current language)
        val localizedName = LanguageUtil.getLocalizedCuisineName(
            holder.itemView.context,
            cuisine.cuisineName
        )

        holder.textCuisineName.text = localizedName

        val imageRes = when (cuisine.cuisineName) {
            "Chinese" -> R.drawable.chinese
            "North Indian" -> R.drawable.north_indian
            "South Indian" -> R.drawable.south_indian
            "Mexican" -> R.drawable.mexican
            "Italian" -> R.drawable.italian
            else -> R.mipmap.ic_launcher
        }
        holder.imageCuisine.setImageResource(imageRes)

        // On click, launch CuisineActivity
        holder.itemView.setOnClickListener {
            val intent = Intent(context, CuisineActivity::class.java)
            intent.putExtra("cuisine_name", cuisine.cuisineName)
            context.startActivity(intent)
        }
    }
}
