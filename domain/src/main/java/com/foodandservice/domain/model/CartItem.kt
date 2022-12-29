package com.foodandservice.domain.model

data class CartItem(
    val id: String,
    val name: String,
    val image: String,
    val price: String,
    val quantity: Int = 0,
    val isExtra: Boolean
)