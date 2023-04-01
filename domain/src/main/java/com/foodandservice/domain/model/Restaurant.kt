package com.foodandservice.domain.model

data class Restaurant(
    val address: RestaurantAddress,
    val banner: String,
    val categoryId: String,
    val categoryName: String,
    val description: String,
    val id: String,
    val logo: String,
    val name: String,
    val openingStatus: String,
    val phone: String,
    val schedule: List<RestaurantSchedule>?
)

data class RestaurantAddress(
    val city: String,
    val country: String,
    val latitude: Double,
    val longitude: Double,
    val name: String,
    val number: Int,
    val postalCode: String
)

data class RestaurantSchedule(
    val endHour: Int,
    val endMinutes: Int,
    val startHour: Int,
    val startMinutes: Int,
    val weekDay: Int
)