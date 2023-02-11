package com.foodandservice.domain.usecases.restaurant

import com.foodandservice.domain.model.RestaurantReview
import com.foodandservice.domain.repository.RestaurantRepository
import com.foodandservice.domain.util.Resource

class GetRestaurantReviewsUseCase(private val restaurantRepository: RestaurantRepository) {
    suspend operator fun invoke(): Resource<List<RestaurantReview>> {
        return restaurantRepository.getRestaurantReviews()
    }
}