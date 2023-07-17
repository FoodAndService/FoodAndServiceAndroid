package com.foodandservice.domain.model.restaurant_details

data class RestaurantProductDetails(
    val category: RestaurantProductDetailsCategory,
    val description: String,
    val dietaryRestrictions: List<RestaurantProductDietaryRestrictionItem>,
    val discountPercentage: Int,
    val discountedPrice: RestaurantProductDiscountedPrice,
    val extras: List<RestaurantProductExtra>,
    val hasDiscount: Boolean,
    val hasStock: Boolean,
    val id: String,
    val image: String,
    val name: String,
    val price: RestaurantProductPrice,
    val productTax: Int,
    val status: String
)