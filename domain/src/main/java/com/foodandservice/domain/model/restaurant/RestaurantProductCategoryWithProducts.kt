package com.foodandservice.domain.model.restaurant

data class RestaurantProductCategoryWithProducts(
    val category: String, val products: List<RestaurantProduct>
)