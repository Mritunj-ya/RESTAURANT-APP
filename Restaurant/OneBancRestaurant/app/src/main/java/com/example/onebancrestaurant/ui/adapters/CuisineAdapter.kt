package com.example.onebancrestaurant.ui.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.onebancrestaurant.R
import com.example.onebancrestaurant.ui.CuisineActivity
import com.example.onebancrestaurant.ui.models.Cuisine

class CuisineAdapter(
    private val context: Context,
    private val cuisines: List<Cuisine>
) : RecyclerView.Adapter<CuisineAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cardView: CardView = view.findViewById(R.id.cardViewCuisine)  // optional, if you want to access CardView directly
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
        holder.textCuisineName.text = cuisine.cuisineName
        val imageRes = when (cuisine.cuisineName) {
            "Chinese" -> R.drawable.chinese
            "North Indian" -> R.drawable.north_indian
            "South Indian" -> R.drawable.south_indian
            "Mexican" -> R.drawable.mexican
            "Italian" -> R.drawable.italian
            else -> R.mipmap.ic_launcher
        }
        holder.imageCuisine.setImageResource(imageRes)

        // Placeholder image (no third-party libs)


        // On click, launch CuisineActivity with cuisine name
        holder.itemView.setOnClickListener {
            val intent = Intent(context, CuisineActivity::class.java)
            intent.putExtra("cuisine_name", cuisine.cuisineName)
            context.startActivity(intent)
        }
    }
}
