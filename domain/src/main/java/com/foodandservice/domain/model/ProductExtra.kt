package com.foodandservice.domain.model

data class ProductExtra(
    val id: String,
    val name: String,
    val price: String,
    val quantity: Int = 0
)