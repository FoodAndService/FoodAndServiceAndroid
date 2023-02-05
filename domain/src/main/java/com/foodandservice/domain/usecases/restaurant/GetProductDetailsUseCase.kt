package com.foodandservice.domain.usecases.restaurant

import com.foodandservice.domain.model.ProductDetails
import com.foodandservice.domain.repository.RestaurantRepository
import com.foodandservice.domain.util.Resource

class GetProductDetailsUseCase(private val restaurantRepository: RestaurantRepository) {
    suspend operator fun invoke(): Resource<ProductDetails> {
        return restaurantRepository.getProductDetails()
    }
}