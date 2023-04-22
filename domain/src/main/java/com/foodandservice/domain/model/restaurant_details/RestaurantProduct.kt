package com.foodandservice.domain.model.restaurant_details

import java.io.Serializable

data class RestaurantProduct(
    val categoryId: String,
    val discountPercentage: Int,
    val discountedPrice: RestaurantProductDiscountedPrice,
    val hasDiscount: Boolean,
    val hasStock: Boolean,
    val id: String,
    val image: String,
    val name: String,
    val price: RestaurantProductPrice,
    val productTax: Int,
    val status: String,
) : Serializable