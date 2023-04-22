package com.foodandservice.data.remote.model.restaurant_details

import com.foodandservice.data.remote.model.restaurant.RestaurantAddressDto
import com.foodandservice.data.remote.model.restaurant.RestaurantScheduleDto
import com.foodandservice.data.remote.model.restaurant.toRestaurantAddress
import com.foodandservice.data.remote.model.restaurant.toRestaurantSchedule
import com.foodandservice.domain.model.restaurant.RestaurantOpeningStatus
import com.foodandservice.domain.model.restaurant_details.RestaurantDetails

data class RestaurantDetailsDto(
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
    val schedule: List<RestaurantScheduleDto>
)

fun RestaurantDetailsDto.toRestaurantDetails() =
    RestaurantDetails(
        address = address.toRestaurantAddress(),
        banner = banner,
        categoryId = categoryId,
        categoryName = categoryName,
        description = description,
        id = id,
        logo = logo,
        name = name,
        openingStatus = RestaurantOpeningStatus.getStatus(openingStatus),
        phone = phone,
        schedule = schedule.map { restaurantScheduleDto -> restaurantScheduleDto.toRestaurantSchedule() })