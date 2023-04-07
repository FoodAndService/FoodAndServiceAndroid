package com.foodandservice.data.remote.model.restaurant

import com.foodandservice.domain.model.RestaurantDetails
import com.foodandservice.domain.model.RestaurantDetailsAddress
import com.foodandservice.domain.model.RestaurantDetailsSchedule
import com.foodandservice.domain.model.RestaurantOpeningStatus

data class RestaurantDetailsDto(
    val address: RestaurantDetailsAddressDto,
    val banner: String,
    val categoryId: String,
    val categoryName: String,
    val description: String,
    val id: String,
    val logo: String,
    val name: String,
    val openingStatus: String,
    val phone: String,
    val schedule: List<RestaurantDetailsScheduleDto>
)

data class RestaurantDetailsAddressDto(
    val city: String,
    val country: String,
    val latitude: Double,
    val longitude: Double,
    val name: String,
    val number: Int,
    val postalCode: String
)

data class RestaurantDetailsScheduleDto(
    val endHour: Int,
    val endMinutes: Int,
    val startHour: Int,
    val startMinutes: Int,
    val weekDay: Int
)

fun RestaurantDetailsDto.toRestaurantDetails() =
    RestaurantDetails(address = address.toRestaurantDetailsAddress(),
        banner = banner,
        categoryId = categoryId,
        categoryName = categoryName,
        description = description,
        id = id,
        logo = logo,
        name = name,
        openingStatus = RestaurantOpeningStatus.getStatus(openingStatus),
        phone = phone,
        schedule = schedule.map { restaurantScheduleDto -> restaurantScheduleDto.toRestaurantDetailsSchedule() })

fun RestaurantDetailsAddressDto.toRestaurantDetailsAddress() = RestaurantDetailsAddress(
    city = city,
    country = country,
    latitude = latitude,
    longitude = longitude,
    name = name,
    number = number,
    postalCode = postalCode
)

fun RestaurantDetailsScheduleDto.toRestaurantDetailsSchedule() = RestaurantDetailsSchedule(
    endHour = endHour,
    endMinutes = endMinutes,
    startHour = startHour,
    startMinutes = startMinutes,
    weekDay = weekDay
)