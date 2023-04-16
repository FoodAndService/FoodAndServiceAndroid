package com.foodandservice.data.remote.model.restaurant

import com.foodandservice.domain.model.restaurant.RestaurantProduct
import com.foodandservice.domain.model.restaurant.RestaurantProductDiscountedPrice
import com.foodandservice.domain.model.restaurant.RestaurantProductPrice

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

data class RestaurantProductPriceDto(
    val currency: String, val printable: String, val value: Int
)

data class RestaurantProductDiscountedPriceDto(
    val currency: String, val printable: String, val value: Int
)

fun RestaurantProductDto.toDomain() = RestaurantProduct(
    categoryId = categoryId,
    discountPercentage = discountPercentage,
    discountedPrice = discountedPrice.toDomain(),
    hasDiscount = hasDiscount,
    hasStock = hasStock,
    id = id,
    image = image,
    name = name,
    price = price.toDomain(),
    productTax = productTax,
    status = status
)

fun RestaurantProductPriceDto.toDomain() = RestaurantProductPrice(
    currency = currency, printable = printable, value = value
)

fun RestaurantProductDiscountedPriceDto.toDomain() = RestaurantProductDiscountedPrice(
    currency = currency, printable = printable, value = value
)