package com.foodandservice.data.remote.model.restaurant_details

import com.foodandservice.domain.model.restaurant_details.RestaurantProductDetailsCategory

data class RestaurantProductDetailsCategoryDto(
    val id: String, val name: String
)

fun RestaurantProductDetailsCategoryDto.toRestaurantProductDetailsCategory() =
    RestaurantProductDetailsCategory(
        id = id, name = name
    )