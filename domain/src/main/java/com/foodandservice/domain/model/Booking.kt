package com.foodandservice.domain.model

import java.time.LocalDateTime

data class Booking(
    val restaurantName: String,
    val diners: Int,
    val isActive: Boolean,
    val dateTime: LocalDateTime
)