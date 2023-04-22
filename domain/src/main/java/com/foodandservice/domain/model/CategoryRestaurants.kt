package com.foodandservice.domain.model

import com.foodandservice.domain.model.restaurant.Restaurant

data class CategoryRestaurants(
    val id: String,
    val name: String,
    val restaurants: List<Restaurant>
)