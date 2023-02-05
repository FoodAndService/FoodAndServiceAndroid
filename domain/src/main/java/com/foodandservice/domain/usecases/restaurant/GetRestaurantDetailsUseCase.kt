package com.foodandservice.domain.usecases.restaurant

import com.foodandservice.domain.model.RestaurantDetails
import com.foodandservice.domain.repository.RestaurantRepository
import com.foodandservice.domain.util.Resource

class GetRestaurantDetailsUseCase(private val restaurantRepository: RestaurantRepository) {
    suspend operator fun invoke(): Resource<RestaurantDetails> {
        return restaurantRepository.getRestaurantDetails()
    }
}