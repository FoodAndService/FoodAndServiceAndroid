package com.foodandservice.domain.model.restaurant_details

data class RestaurantProductDetails(
    val allergensAndIntolerances: List<RestaurantProductAllergenIntolerance>,
    val category: RestaurantProductDetailsCategory,
    val description: String,
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