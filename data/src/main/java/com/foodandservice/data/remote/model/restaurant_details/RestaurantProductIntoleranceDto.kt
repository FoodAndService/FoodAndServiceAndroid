package com.foodandservice.data.remote.model.restaurant_details

import com.foodandservice.domain.model.restaurant_details.RestaurantProductIntolerance

data class RestaurantProductIntoleranceDto(
    val id: String, val name: String
)

fun RestaurantProductIntoleranceDto.toRestaurantProductIntolerance() = RestaurantProductIntolerance(
    id = id, name = name
)