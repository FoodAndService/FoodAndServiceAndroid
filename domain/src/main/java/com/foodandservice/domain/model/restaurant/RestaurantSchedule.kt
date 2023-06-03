package com.foodandservice.domain.model.restaurant

import java.io.Serializable

data class RestaurantSchedule(
    val endHour: Int,
    val endMinutes: Int,
    val startHour: Int,
    val startMinutes: Int,
    val weekDay: Int
) : Serializable