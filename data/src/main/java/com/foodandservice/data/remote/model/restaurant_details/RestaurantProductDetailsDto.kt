package com.foodandservice.data.remote.model.restaurant_details

import com.foodandservice.domain.model.restaurant_details.RestaurantProductDetails

data class RestaurantProductDetailsDto(
    val allergens: List<RestaurantProductAllergenDto>,
    val category: RestaurantProductDetailsCategoryDto,
    val createdAt: String,
    val defaultLanguage: String,
    val description: String,
    val discountPercentage: Int,
    val discountedPrice: RestaurantProductDiscountedPriceDto,
    val extras: List<RestaurantProductExtraDto>,
    val hasDiscount: Boolean,
    val hasStock: Boolean,
    val id: String,
    val image: String,
    val intolerances: List<RestaurantProductIntoleranceDto>,
    val name: String,
    val others: List<RestaurantProductOtherDto>,
    val price: RestaurantProductPriceDto,
    val productTax: Int,
    val status: String,
    val updatedAt: String
)

fun RestaurantProductDetailsDto.toRestaurantProductDetails() = RestaurantProductDetails(
    allergensAndIntolerances =
    allergens.map { allergen -> allergen.toRestaurantProductAllergenIntolerance() }
        .plus(intolerances.map { intolerance -> intolerance.toRestaurantProductIntolerance() })
        .plus(others.map { other -> other.toRestaurantProductAllergenIntolerance() }),
    category = category.toRestaurantProductDetailsCategory(),
    description = description,
    discountPercentage = discountPercentage,
    discountedPrice = discountedPrice.toRestaurantProductDiscountedPrice(),
    extras = extras.map { extra -> extra.toRestaurantProductExtra() },
    hasDiscount = hasDiscount,
    hasStock = hasStock,
    id = id,
    image = image,
    name = name,
    price = price.toRestaurantProductPrice(),
    productTax = productTax,
    status = status
)