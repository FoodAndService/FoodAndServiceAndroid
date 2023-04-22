package com.foodandservice.domain.usecases.restaurant

import com.foodandservice.domain.model.restaurant_details.RestaurantDetails
import com.foodandservice.domain.repository.CustomerRepository
import com.foodandservice.domain.util.ApiResponse

class GetRestaurantDetailsUseCase(private val customerRepository: CustomerRepository) {
    suspend operator fun invoke(restaurantId: String): ApiResponse<RestaurantDetails> {
        return customerRepository.getRestaurantDetails(restaurantId)
    }
}