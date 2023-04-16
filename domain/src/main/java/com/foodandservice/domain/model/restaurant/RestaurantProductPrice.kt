package com.foodandservice.domain.model.restaurant

data class RestaurantProductPrice(
    val currency: String, val printable: String, val value: Int
)

fun RestaurantProductPrice.toUI(): String {
    val formattedPrice = "%.2f".format(printable.toDouble()).replace(".", ",")

    val currencySymbol = when (currency) {
        "EUR" -> "€"
        "USD" -> "$"
        else -> currency
    }

    return "$formattedPrice$currencySymbol"
}