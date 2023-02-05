package com.foodandservice.domain.model

data class RestaurantDetailsExtra(
    val id: String,
    val name: String,
    val latLng: Pair<Double, Double>,
    val schedule: String,
    val phone: String
)