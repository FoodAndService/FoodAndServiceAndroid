package com.foodandservice.domain.model

data class OrderProduct(
    val id: String,
    val name: String,
    val image: String,
    val price: String,
    val isExtra: Boolean
)