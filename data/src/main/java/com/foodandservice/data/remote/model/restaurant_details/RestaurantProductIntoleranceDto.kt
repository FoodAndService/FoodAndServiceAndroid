package com.foodandservice.data.remote.model.restaurant_details

import com.foodandservice.domain.model.restaurant_details.RestaurantProductAllergenIntolerance

data class RestaurantProductIntoleranceDto(
    val id: String, val name: String
)

fun RestaurantProductIntoleranceDto.toRestaurantProductIntolerance() =
    RestaurantProductAllergenIntolerance(
        id = id, name = name
    )