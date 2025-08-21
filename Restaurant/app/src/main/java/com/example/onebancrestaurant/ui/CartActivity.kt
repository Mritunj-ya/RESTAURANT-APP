package com.example.onebancrestaurant.ui

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.onebancrestaurant.R
import com.example.onebancrestaurant.ui.adapters.CartAdapter
import com.example.onebancrestaurant.ui.utils.CartManager

class CartActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var totalText: TextView
    private lateinit var cgstText: TextView
    private lateinit var sgstText: TextView
    private lateinit var grandTotalText: TextView
    private lateinit var placeOrderButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        recyclerView = findViewById(R.id.cartRecyclerView)
        totalText = findViewById(R.id.netTotal)
        cgstText = findViewById(R.id.cgst)
        sgstText = findViewById(R.id.sgst)
        grandTotalText = findViewById(R.id.grandTotal)
        placeOrderButton = findViewById(R.id.placeOrderButton)

        val cartItems = CartManager.getCartItems()
        Log.d("DEBUG", "cartItems = $cartItems")

        if (cartItems.isEmpty()) {
            Toast.makeText(this, "Cart is empty", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CartAdapter(cartItems)
        Log.d("DEBUG", "RecyclerView = $recyclerView")

        val netTotal = CartManager.getNetTotal()
        val tax = netTotal * 0.025
        val grandTotal = netTotal + (2 * tax)
        Log.d("DEBUG", "netTotal = $netTotal")

        totalText.text = getString(R.string.net_total, netTotal)
        cgstText.text = getString(R.string.cgst, tax)
        sgstText.text = getString(R.string.sgst, tax)
        grandTotalText.text = getString(R.string.grand_total, grandTotal)

        placeOrderButton.setOnClickListener {
            Toast.makeText(this, "Order Placed Successfully", Toast.LENGTH_SHORT).show()
            CartManager.clearCart()
            finish()
        }
    }
}
