package com.foodandservice.data.remote.model.restaurant_details

import com.foodandservice.domain.model.restaurant_details.RestaurantProductCategory

data class RestaurantProductCategoryDto(
    val createdAt: String,
    val defaultLanguage: String,
    val id: String,
    val language: String,
    val name: String,
    val order: Int,
    val updatedAt: String
)

fun RestaurantProductCategoryDto.toRestaurantProductCategory() = RestaurantProductCategory(
    id = id, name = name, order = order
)