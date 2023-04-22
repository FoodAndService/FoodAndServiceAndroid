package com.foodandservice.data.remote.model.restaurant_details

import com.foodandservice.domain.model.restaurant_details.RestaurantProductAllergen

data class RestaurantProductAllergenDto(
    val id: String, val name: String
)

fun RestaurantProductAllergenDto.toRestaurantProductAllergen() =
    RestaurantProductAllergen(id = id, name = name)