package com.foodandservice.data.remote.model.restaurant_details

import com.foodandservice.domain.model.restaurant_details.RestaurantProductPrice

data class RestaurantProductPriceDto(
    val currency: String, val printable: String, val value: Int
)

fun RestaurantProductPriceDto.toRestaurantProductPrice() = RestaurantProductPrice(
    currency = currency, printable = printable, value = value
)