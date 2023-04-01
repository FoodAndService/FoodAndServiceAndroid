package com.foodandservice.domain.usecases.restaurant

import com.foodandservice.domain.model.RestaurantReview
import com.foodandservice.domain.repository.CustomerRepository
import com.foodandservice.domain.util.Resource

class GetRestaurantReviewsUseCase(private val customerRepository: CustomerRepository) {
    suspend operator fun invoke(): Resource<List<RestaurantReview>> {
        return customerRepository.getRestaurantReviews()
    }
}