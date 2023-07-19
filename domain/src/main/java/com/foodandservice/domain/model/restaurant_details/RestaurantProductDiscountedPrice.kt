package com.foodandservice.domain.model.restaurant_details

data class RestaurantProductDiscountedPrice(
    val currency: String, val printable: String, val value: Int
)

fun RestaurantProductDiscountedPrice.toUI(quantity: Int = 1): String {
    val formattedPrice =
        "%.2f".format(printable.replace(",", ".").toDouble() * quantity).replace(".", ",")

    val currencySymbol = when (currency) {
        "EUR" -> "€"
        "USD" -> "$"
        else -> currency
    }

    return "$formattedPrice$currencySymbol"
}