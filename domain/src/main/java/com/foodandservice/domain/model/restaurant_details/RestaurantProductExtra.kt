package com.foodandservice.domain.model.restaurant_details

data class RestaurantProductExtra(
    val id: String,
    val name: String,
    val price: RestaurantProductPrice
) {
    var quantity: Int = 0

    fun incrementQuantity() {
        quantity += 1
    }

    fun decrementQuantity() {
        quantity -= 1
    }
}