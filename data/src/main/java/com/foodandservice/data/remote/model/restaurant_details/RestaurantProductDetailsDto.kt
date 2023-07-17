package com.foodandservice.data.remote.model.restaurant_details

import com.foodandservice.domain.model.restaurant_details.RestaurantProductDetails
import com.foodandservice.domain.model.restaurant_details.RestaurantProductDietaryRestriction
import com.foodandservice.domain.model.restaurant_details.RestaurantProductDietaryRestrictionItem

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

fun RestaurantProductDetailsDto.toRestaurantProductDetails(): RestaurantProductDetails {
    val dietaryRestrictionItems = mutableListOf<RestaurantProductDietaryRestrictionItem>()

    if (allergens.isNotEmpty()) {
        dietaryRestrictionItems.add(RestaurantProductDietaryRestrictionItem.Header("Allergens"))
        dietaryRestrictionItems.addAll(allergens.map { allergen ->
            RestaurantProductDietaryRestrictionItem.Item(
                RestaurantProductDietaryRestriction(
                    id = allergen.id, name = allergen.name
                )
            )
        })
    }
    if (intolerances.isNotEmpty()) {
        dietaryRestrictionItems.add(RestaurantProductDietaryRestrictionItem.Header("Intolerances"))
        dietaryRestrictionItems.addAll(intolerances.map { intolerance ->
            RestaurantProductDietaryRestrictionItem.Item(
                RestaurantProductDietaryRestriction(
                    id = intolerance.id, name = intolerance.name
                )
            )
        })
    }
    if (others.isNotEmpty()) {
        dietaryRestrictionItems.add(RestaurantProductDietaryRestrictionItem.Header("Others"))
        dietaryRestrictionItems.addAll(others.map { other ->
            RestaurantProductDietaryRestrictionItem.Item(
                RestaurantProductDietaryRestriction(
                    id = other.id, name = other.name
                )
            )
        })
    }

    return RestaurantProductDetails(
        dietaryRestrictions = dietaryRestrictionItems,
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
}