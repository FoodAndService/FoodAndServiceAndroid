package com.foodandservice.domain.model

import java.io.Serializable

data class ProductDetails(
    val id: String,
    val allergensAndIntolerances: List<AllergenIntolerance>,
    val productExtras: List<ProductExtra>
) : Serializable