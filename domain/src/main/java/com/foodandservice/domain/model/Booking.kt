package com.foodandservice.domain.model

import java.time.LocalDateTime

data class Booking(
    val id: String,
    val restaurantName: String,
    val diners: Int,
    val isActive: Boolean,
    val dateTime: LocalDateTime
)