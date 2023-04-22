package com.foodandservice.data.remote.model.restaurant_details

import com.foodandservice.domain.model.restaurant_details.RestaurantProductOther

data class RestaurantProductOtherDto(
    val id: String,
    val name: String
)

fun RestaurantProductOtherDto.toRestaurantProductOther() = RestaurantProductOther(
    id = id,
    name = name
)