package com.foodandservice.domain.usecases.restaurant

import com.foodandservice.domain.model.restaurant.RestaurantProductCategoryWithProducts
import com.foodandservice.domain.repository.CustomerRepository
import com.foodandservice.domain.util.ApiResponse

class GetRestaurantProductCategoriesWithProductsUseCase(private val customerRepository: CustomerRepository) {
    suspend operator fun invoke(restaurantId: String): ApiResponse<List<RestaurantProductCategoryWithProducts>> {
        return customerRepository.getRestaurantProductCategoriesWithProducts(restaurantId)
    }
}