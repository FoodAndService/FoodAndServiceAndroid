package com.foodandservice.domain.model

data class Product(
    val id: String,
    val name: String,
    val image: String,
    val inStock: Boolean,
    val isRefill: Boolean,
    val price: String
)