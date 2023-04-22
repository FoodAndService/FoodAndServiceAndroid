package com.foodandservice.domain.model.restaurant

data class RestaurantAddress(
    val city: String,
    val country: String,
    val latitude: Double,
    val longitude: Double,
    val name: String,
    val number: Int,
    val postalCode: String
)