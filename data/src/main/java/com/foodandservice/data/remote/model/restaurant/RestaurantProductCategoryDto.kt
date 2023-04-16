package com.foodandservice.data.remote.model.restaurant

import com.foodandservice.domain.model.restaurant.RestaurantProductCategory

data class RestaurantProductCategoryDto(
    val createdAt: String,
    val defaultLanguage: String,
    val id: String,
    val language: String,
    val name: String,
    val order: Int,
    val updatedAt: String
)

fun RestaurantProductCategoryDto.toDomain() = RestaurantProductCategory(
    id = id, name = name, order = order
)