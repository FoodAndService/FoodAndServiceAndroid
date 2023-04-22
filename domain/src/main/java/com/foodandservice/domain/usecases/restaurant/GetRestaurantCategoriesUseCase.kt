package com.foodandservice.domain.usecases.restaurant

import com.foodandservice.domain.model.restaurant.RestaurantCategory
import com.foodandservice.domain.repository.CustomerRepository
import com.foodandservice.domain.util.ApiResponse

class GetRestaurantCategoriesUseCase(private val customerRepository: CustomerRepository) {
    suspend operator fun invoke(): ApiResponse<List<RestaurantCategory>> {
        return customerRepository.getRestaurantCategories()
    }
}