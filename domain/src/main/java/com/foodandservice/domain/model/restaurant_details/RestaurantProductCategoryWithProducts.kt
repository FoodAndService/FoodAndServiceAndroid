package com.foodandservice.domain.model.restaurant_details

data class RestaurantProductCategoryWithProducts(
    val category: String, val products: List<RestaurantProduct>
)