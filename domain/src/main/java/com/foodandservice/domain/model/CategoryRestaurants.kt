package com.foodandservice.domain.model

data class CategoryRestaurants(
    val id: String,
    val name: String,
    val restaurants: List<Restaurant>
)