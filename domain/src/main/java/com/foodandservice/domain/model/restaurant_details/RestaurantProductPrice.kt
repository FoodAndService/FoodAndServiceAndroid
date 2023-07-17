package com.foodandservice.domain.model.restaurant_details

data class RestaurantProductPrice(
    val currency: String, val printable: String, val value: Int
)

fun RestaurantProductPrice.toUI(quantity: Int = 1): String {
    val formattedPrice = "%.2f".format(printable.toDouble() * quantity).replace(".", ",")

    val currencySymbol = when (currency) {
        "EUR" -> "€"
        "USD" -> "$"
        else -> currency
    }

    return "$formattedPrice$currencySymbol"
}