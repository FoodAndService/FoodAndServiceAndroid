package com.foodandservice.domain.model.restaurant_details

sealed class RestaurantProductDietaryRestrictionItem {
    data class Header(val title: String) : RestaurantProductDietaryRestrictionItem()
    data class Item(val dietarytRestriction: RestaurantProductDietaryRestriction) :
        RestaurantProductDietaryRestrictionItem()
}