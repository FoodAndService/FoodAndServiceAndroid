package com.foodandservice.domain.model

import java.io.Serializable

data class Product(
    val id: String,
    val name: String,
    val image: String,
    val inStock: Boolean,
    val isRefill: Boolean,
    val price: String
) : Serializable