package com.foodandservice.domain.usecases.restaurant

import com.foodandservice.domain.model.RestaurantDetailsExtra
import com.foodandservice.domain.repository.CustomerRepository
import com.foodandservice.domain.util.Resource

class GetRestaurantDetailsExtraUseCase(private val customerRepository: CustomerRepository) {
    suspend operator fun invoke(): Resource<RestaurantDetailsExtra> {
        return customerRepository.getRestaurantDetailsExtra()
    }
}