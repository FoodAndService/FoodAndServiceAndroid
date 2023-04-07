package com.foodandservice.domain.usecases.restaurant

import com.foodandservice.domain.model.ProductDetails
import com.foodandservice.domain.repository.CustomerRepository
import com.foodandservice.domain.util.ApiResponse

class GetProductDetailsUseCase(private val customerRepository: CustomerRepository) {
    suspend operator fun invoke(): ApiResponse<ProductDetails> {
        return customerRepository.getProductDetails()
    }
}