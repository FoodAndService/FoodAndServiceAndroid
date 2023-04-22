package com.foodandservice.data.remote.model.restaurant_details

import com.foodandservice.domain.model.restaurant_details.RestaurantProduct

data class RestaurantProductDto(
    val categoryId: String,
    val createdAt: String,
    val discountPercentage: Int,
    val discountedPrice: RestaurantProductDiscountedPriceDto,
    val hasDiscount: Boolean,
    val hasStock: Boolean,
    val id: String,
    val image: String,
    val name: String,
    val price: RestaurantProductPriceDto,
    val productTax: Int,
    val status: String,
    val updatedAt: String
)

fun RestaurantProductDto.toRestaurantProductPrice() = RestaurantProduct(
    categoryId = categoryId,
    discountPercentage = discountPercentage,
    discountedPrice = discountedPrice.toRestaurantProductDiscountedPrice(),
    hasDiscount = hasDiscount,
    hasStock = hasStock,
    id = id,
    image = image,
    name = name,
    price = price.toRestaurantProductPrice(),
    productTax = productTax,
    status = status
)