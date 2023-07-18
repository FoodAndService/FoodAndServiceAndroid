package com.foodandservice.domain.model.restaurant_details

data class RestaurantProductExtra(
    val id: String,
    val name: String,
    val price: RestaurantProductPrice
) {
    var quantity: Int = 0

    fun incrementQuantity() {
        if (quantity < 100) quantity += 1
    }

    fun decrementQuantity() {
        if (quantity > 0) quantity -= 1
    }
}