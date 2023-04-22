package com.foodandservice.data.remote.model.restaurant_details

import com.foodandservice.domain.model.restaurant_details.RestaurantProductDiscountedPrice

data class RestaurantProductDiscountedPriceDto(
    val currency: String, val printable: String, val value: Int
)

fun RestaurantProductDiscountedPriceDto.toRestaurantProductDiscountedPrice() =
    RestaurantProductDiscountedPrice(
        currency = currency, printable = printable, value = value
    )