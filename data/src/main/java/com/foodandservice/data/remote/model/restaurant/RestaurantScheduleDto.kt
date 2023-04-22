package com.foodandservice.data.remote.model.restaurant

import com.foodandservice.domain.model.restaurant.RestaurantSchedule

data class RestaurantScheduleDto(
    val endHour: Int,
    val endMinutes: Int,
    val startHour: Int,
    val startMinutes: Int,
    val weekDay: Int
)

fun RestaurantScheduleDto.toRestaurantSchedule() = RestaurantSchedule(
    endHour = endHour,
    endMinutes = endMinutes,
    startHour = startHour,
    startMinutes = startMinutes,
    weekDay = weekDay
)