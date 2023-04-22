package com.foodandservice.domain.usecases.restaurant

import com.foodandservice.domain.model.restaurant_details.RestaurantProductDetails
import com.foodandservice.domain.repository.CustomerRepository
import com.foodandservice.domain.util.ApiResponse

class GetRestaurantProductDetailsUseCase(private val customerRepository: CustomerRepository) {
    suspend operator fun invoke(
        restaurantId: String, productId: String
    ): ApiResponse<RestaurantProductDetails> {
        return customerRepository.getRestaurantProductDetails(
            restaurantId = restaurantId, productId = productId
        )
    }
}