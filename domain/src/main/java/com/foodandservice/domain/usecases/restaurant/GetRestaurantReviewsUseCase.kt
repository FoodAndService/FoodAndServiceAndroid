package com.foodandservice.domain.usecases.restaurant

import com.foodandservice.domain.model.RestaurantReview
import com.foodandservice.domain.repository.CustomerRepository
import com.foodandservice.domain.util.ApiResponse

class GetRestaurantReviewsUseCase(private val customerRepository: CustomerRepository) {
    suspend operator fun invoke(): ApiResponse<List<RestaurantReview>> {
        return customerRepository.getRestaurantReviews()
    }
}