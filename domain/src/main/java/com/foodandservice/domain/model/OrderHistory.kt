package com.foodandservice.domain.model

import java.time.LocalDateTime

data class OrderHistory(
    val restaurantName: String,
    val amount: String,
    val date: LocalDateTime
)