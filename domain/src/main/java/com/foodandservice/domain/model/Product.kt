package com.foodandservice.domain.model

data class Product(
    val id: Int,
    val image: Int,
    var name: String,
    val refill: Boolean = false,
    val price: String
)