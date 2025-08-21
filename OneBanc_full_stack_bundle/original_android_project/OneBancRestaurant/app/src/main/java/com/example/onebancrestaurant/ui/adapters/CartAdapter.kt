package com.example.onebancrestaurant.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.onebancrestaurant.R
import com.example.onebancrestaurant.ui.models.Dish


class CartAdapter(private val items: List<Dish>) :
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.dishName)
        val quantity: TextView = itemView.findViewById(R.id.dishQuantity)
        val price: TextView = itemView.findViewById(R.id.dishPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart_dish, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val dish = items[position]
        holder.name.text = dish.name
        val context = holder.itemView.context
        holder.quantity.text = context.getString(R.string.qty_format, dish.quantity)
        holder.price.text = context.getString(
            R.string.price_format,
            (dish.price * dish.quantity).toDouble()
        )

    }

    override fun getItemCount(): Int = items.size
}
