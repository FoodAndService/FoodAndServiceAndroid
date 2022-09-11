package com.foodandservice.domain.model

data class CategoryWithProducts(
    val categoryName: String,
    val products: List<Product>
)
