package com.foodandservice.domain.model

import java.time.LocalDateTime

data class Order(
    val id: String,
    val restaurantName: String,
    val price: String,
    val date: LocalDateTime
)