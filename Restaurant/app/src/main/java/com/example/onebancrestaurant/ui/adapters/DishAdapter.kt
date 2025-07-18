package com.example.onebancrestaurant.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.onebancrestaurant.R
import com.example.onebancrestaurant.ui.models.Dish
import com.example.onebancrestaurant.ui.utils.CartManager
import com.example.onebancrestaurant.utils.LanguageUtil


class DishAdapter(private val dishes: List<Dish>) : RecyclerView.Adapter<DishAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageDish: ImageView = view.findViewById(R.id.imageDish)
        val dishName: TextView = view.findViewById(R.id.textDishName)
        val price: TextView = view.findViewById(R.id.textDishPrice)
        val rating: TextView = view.findViewById(R.id.textDishRating)
        val btnPlus: Button = view.findViewById(R.id.btnPlus)
        val btnMinus: Button = view.findViewById(R.id.btnMinus)
        val quantityText: TextView = view.findViewById(R.id.textQuantity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_dish, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = dishes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dish = dishes[position]
        val context = holder.itemView.context
        holder.imageDish.setImageResource(dish.imageRes)
        holder.dishName.text = dish.name
        holder.price.text = context.getString(R.string.dish_price, dish.price.toFloat())
        holder.rating.text = context.getString(R.string.dish_rating, dish.rating)
        holder.quantityText.text = dish.quantity.toString()

        holder.dishName.text = LanguageUtil.getLocalizedDishName(holder.itemView.context, dish.name)

        holder.btnPlus.setOnClickListener {
            dish.quantity++
            holder.quantityText.text = dish.quantity.toString()

            // Always add a fresh copy to avoid reference issues
            CartManager.addToCart(
                Dish(
                    id = dish.id,
                    name = dish.name,
                    imageRes = dish.imageRes,
                    price = dish.price,
                    rating = dish.rating,
                    quantity = 1 // Add only 1 at a time
                )
            )

            Toast.makeText(
                holder.itemView.context,
                "${dish.name} added to cart",
                Toast.LENGTH_SHORT
            ).show()
        }

        holder.btnMinus.setOnClickListener {
            if (dish.quantity > 0) {
                dish.quantity--
                holder.quantityText.text = dish.quantity.toString()

                // If quantity becomes 0, remove it from cart
                if (dish.quantity == 0) {
                    CartManager.removeFromCart(dish.id)
                } else {
                    CartManager.updateQuantity(dish.id, dish.quantity)
                }
            }
        }
    }
}
