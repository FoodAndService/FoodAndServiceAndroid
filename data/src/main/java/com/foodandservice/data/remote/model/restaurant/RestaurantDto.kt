package com.foodandservice.data.remote.model.restaurant

import com.foodandservice.domain.model.restaurant.Restaurant
import com.foodandservice.domain.model.restaurant.RestaurantOpeningStatus

data class RestaurantDto(
    val address: RestaurantAddressDto,
    val banner: String,
    val categoryId: String,
    val categoryName: String,
    val description: String,
    val id: String,
    val logo: String,
    val name: String,
    val openingStatus: String,
    val phone: String,
    val schedule: List<RestaurantScheduleDto>?
)

fun RestaurantDto.toRestaurant() = Restaurant(address = address.toRestaurantAddress(),
    banner = banner,
    categoryId = categoryId,
    categoryName = categoryName,
    description = description,
    id = id,
    logo = logo,
    name = name,
    openingStatus = RestaurantOpeningStatus.getStatus(openingStatus),
    phone = phone,
    schedule = schedule?.map { restaurantScheduleDto -> restaurantScheduleDto.toRestaurantSchedule() })