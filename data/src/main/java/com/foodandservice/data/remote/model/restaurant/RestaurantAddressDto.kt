package com.foodandservice.data.remote.model.restaurant

import com.foodandservice.domain.model.restaurant.RestaurantAddress

data class RestaurantAddressDto(
    val city: String,
    val country: String,
    val latitude: Double,
    val longitude: Double,
    val name: String,
    val number: Int,
    val postalCode: String
)

fun RestaurantAddressDto.toRestaurantAddress() = RestaurantAddress(
    city = city,
    country = country,
    latitude = latitude,
    longitude = longitude,
    name = name,
    number = number,
    postalCode = postalCode
)