package com.foodandservice.domain.model.restaurant_details

data class RestaurantProductExtra(
    val id: String,
    val name: String,
    val price: RestaurantProductPrice
)