package com.foodandservice.domain.model

import java.time.LocalDateTime

data class RestaurantReview(
    val id: String,
    val clientName: String,
    val rating: Float,
    val description: String,
    val isOwnerAnswer: Boolean,
    val date: LocalDateTime
)
