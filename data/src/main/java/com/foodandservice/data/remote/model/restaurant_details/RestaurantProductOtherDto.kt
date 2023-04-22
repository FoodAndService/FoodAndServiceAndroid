package com.foodandservice.data.remote.model.restaurant_details

import com.foodandservice.domain.model.restaurant_details.RestaurantProductAllergenIntolerance

data class RestaurantProductOtherDto(
    val id: String, val name: String
)

fun RestaurantProductOtherDto.toRestaurantProductAllergenIntolerance() =
    RestaurantProductAllergenIntolerance(
        id = id, name = name
    )