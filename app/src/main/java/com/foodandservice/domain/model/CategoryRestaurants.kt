package com.foodandservice.domain.model

data class CategoryRestaurants(
    val id: Int,
    val name: String,
    val restaurants: List<Restaurant>
)