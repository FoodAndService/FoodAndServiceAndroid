package com.foodandservice.domain.model

data class RestaurantDetails(
    val id: String,
    val name: String,
    val rating: Float,
    val distance: Int,
    val categoriesWithProducts: List<CategoryWithProducts>
)