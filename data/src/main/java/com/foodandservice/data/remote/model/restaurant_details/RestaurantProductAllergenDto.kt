package com.foodandservice.data.remote.model.restaurant_details

import com.foodandservice.domain.model.restaurant_details.RestaurantProductAllergenIntolerance

data class RestaurantProductAllergenDto(
    val id: String, val name: String
)

fun RestaurantProductAllergenDto.toRestaurantProductAllergenIntolerance() =
    RestaurantProductAllergenIntolerance(id = id, name = name)