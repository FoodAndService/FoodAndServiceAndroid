package com.foodandservice.data.remote.model.restaurant_details

import com.foodandservice.domain.model.restaurant_details.RestaurantProductExtra

data class RestaurantProductExtraDto(
    val id: String, val name: String, val price: RestaurantProductPriceDto
)

fun RestaurantProductExtraDto.toRestaurantProductExtra() = RestaurantProductExtra(
    id = id, name = name, price = price.toRestaurantProductPrice()
)