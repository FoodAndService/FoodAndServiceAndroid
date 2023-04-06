package com.foodandservice.domain.usecases.restaurant

import com.foodandservice.domain.model.RestaurantCategory
import com.foodandservice.domain.repository.CustomerRepository
import com.foodandservice.domain.util.Resource

class GetRestaurantCategoriesUseCase(private val customerRepository: CustomerRepository) {
    suspend operator fun invoke(): Resource<List<RestaurantCategory>> {
        return customerRepository.getRestaurantCategories()
    }
}