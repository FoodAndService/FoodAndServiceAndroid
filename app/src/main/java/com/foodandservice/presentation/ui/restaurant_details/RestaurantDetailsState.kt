package com.foodandservice.presentation.ui.restaurant_details

import com.foodandservice.domain.model.restaurant_details.RestaurantDetails
import com.foodandservice.domain.model.restaurant_details.RestaurantProductCategoryWithProducts

sealed class RestaurantDetailsState {
    data class DetailsSuccess(val restaurantDetails: RestaurantDetails) : RestaurantDetailsState()
    data class ProductsWithCategoriesSuccess(val restaurantProductCategoriesWithProducts: List<RestaurantProductCategoryWithProducts>) :
        RestaurantDetailsState()

    data class Error(val message: String) : RestaurantDetailsState()
    object Loading : RestaurantDetailsState()
    object Idle : RestaurantDetailsState()
}