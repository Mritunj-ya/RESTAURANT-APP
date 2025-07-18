package com.example.onebancrestaurant.ui.utils

import android.os.Handler
import android.os.Looper
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

object ApiClient {

    private val client = OkHttpClient()

    /**
     * Async POST request using OkHttp (used for payment or background API calls)
     */
    fun makePayment(jsonBody: String, callback: (String?) -> Unit) {
        val requestBody = RequestBody.create(
            "application/json; charset=utf-8".toMediaTypeOrNull(),
            jsonBody
        )

        val request = Request.Builder()
            .url("https://your-api-endpoint.com/api/payment") // 🔁 Replace with real endpoint
            .post(requestBody)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Handler(Looper.getMainLooper()).post {
                    callback(null)
                }
            }

            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body?.string()
                Handler(Looper.getMainLooper()).post {
                    callback(responseBody)
                }
            }
        })
    }

    /**
     * Synchronous POST using HttpURLConnection (used when blocking is acceptable)
     */
    fun post(endpoint: String, body: String): String {
        val url = URL("https://api.onebanc.ai$endpoint")
        val connection = url.openConnection() as HttpURLConnection

        connection.requestMethod = "POST"
        connection.setRequestProperty("Content-Type", "application/json")
        connection.doOutput = true

        connection.outputStream.use {
            it.write(body.toByteArray())
        }

        return connection.inputStream.bufferedReader().use { it.readText() }
    }
}
