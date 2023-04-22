package com.foodandservice.domain.model.restaurant

data class RestaurantSchedule(
    val endHour: Int,
    val endMinutes: Int,
    val startHour: Int,
    val startMinutes: Int,
    val weekDay: Int
)