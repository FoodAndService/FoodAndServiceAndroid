package com.foodandservice.domain.usecases.restaurant

import com.foodandservice.domain.model.RestaurantCategoryTag
import com.foodandservice.domain.repository.CustomerRepository
import com.foodandservice.domain.util.Resource

class GetRestaurantCategoriesUseCase(private val customerRepository: CustomerRepository) {
    suspend operator fun invoke(): Resource<List<RestaurantCategoryTag>> {
        return customerRepository.getRestaurantTags()
    }
}