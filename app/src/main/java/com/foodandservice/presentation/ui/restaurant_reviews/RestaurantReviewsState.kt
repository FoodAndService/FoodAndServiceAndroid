package com.foodandservice.presentation.ui.restaurant_reviews

import com.foodandservice.domain.model.RestaurantReview

sealed class RestaurantReviewsState {
    data class Success(val restaurantReviews: List<RestaurantReview>) : RestaurantReviewsState()
    data class Error(val message: String) : RestaurantReviewsState()
    object Loading : RestaurantReviewsState()
    object Idle : RestaurantReviewsState()
}