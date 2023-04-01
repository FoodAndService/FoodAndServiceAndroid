package com.foodandservice.domain.usecases.restaurant

import com.foodandservice.domain.model.RestaurantDetails
import com.foodandservice.domain.repository.CustomerRepository
import com.foodandservice.domain.util.Resource

class GetRestaurantDetailsUseCase(private val customerRepository: CustomerRepository) {
    suspend operator fun invoke(): Resource<RestaurantDetails> {
        return customerRepository.getRestaurantDetails()
    }
}