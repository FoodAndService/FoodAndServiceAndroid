package com.foodandservice.domain.model.restaurant_details

data class RestaurantProductPrice(
    val currency: String, val printable: String, val value: Int
)

fun RestaurantProductPrice.toUI(quantity: Int = 1): String {
    val formattedPrice =
        "%.2f".format(printable.replace(",", ".").toDouble() * quantity).replace(".", ",")

    val currencySymbol = when (currency) {
        "EUR" -> "€"
        "USD" -> "$"
        else -> currency
    }

    return "$formattedPrice$currencySymbol"
}

fun toPricePrintable(value: Int): String {
    val formattedValue = value / 100.0
    return "%.2f".format(formattedValue)
}