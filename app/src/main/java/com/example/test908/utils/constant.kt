package com.example.test908.utils

import android.content.Context
import android.view.View
import android.widget.Toast

object constant {
        val BASE_URL = "https://api.nytimes.com/svc/movies/v2/"
        val API_KEY = "GW5a0tJfWOcfQ7k3dpQizIsrmpZ33Bmm"
}
fun View.visible(isVisible: Boolean) {
        visibility = if (isVisible) View.VISIBLE else View.GONE
}
enum class ApiStatus{
        LOADING, ERROR, DONE}
fun Context.toast(message: String) {
        Toast.makeText(
                this,
                message,
                Toast.LENGTH_LONG
        ).show()
}