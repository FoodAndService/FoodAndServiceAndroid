package com.foodandservice.data.remote.model.restaurant

import com.foodandservice.domain.model.Restaurant
import com.foodandservice.domain.model.RestaurantAddress
import com.foodandservice.domain.model.RestaurantOpeningStatus
import com.foodandservice.domain.model.RestaurantSchedule

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

data class RestaurantAddressDto(
    val city: String,
    val country: String,
    val latitude: Double,
    val longitude: Double,
    val name: String,
    val number: Int,
    val postalCode: String
)

data class RestaurantScheduleDto(
    val endHour: Int,
    val endMinutes: Int,
    val startHour: Int,
    val startMinutes: Int,
    val weekDay: Int
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

fun RestaurantAddressDto.toRestaurantAddress() = RestaurantAddress(
    city = city,
    country = country,
    latitude = latitude,
    longitude = longitude,
    name = name,
    number = number,
    postalCode = postalCode
)

fun RestaurantScheduleDto.toRestaurantSchedule() = RestaurantSchedule(
    endHour = endHour,
    endMinutes = endMinutes,
    startHour = startHour,
    startMinutes = startMinutes,
    weekDay = weekDay
)