package com.foodandservice.domain.usecases.restaurant

import com.foodandservice.domain.repository.CustomerRepository

class GetCategoryRestaurantsUseCase(private val customerRepository: CustomerRepository) {
//    suspend operator fun invoke(category: String): Resource<List<Restaurant>> {
//        return customerRepository.getCategoryRestaurants(category = category)
//    }
}