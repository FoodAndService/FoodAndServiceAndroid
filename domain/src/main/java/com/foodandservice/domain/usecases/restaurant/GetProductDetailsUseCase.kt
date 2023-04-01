package com.foodandservice.domain.usecases.restaurant

import com.foodandservice.domain.model.ProductDetails
import com.foodandservice.domain.repository.CustomerRepository
import com.foodandservice.domain.util.Resource

class GetProductDetailsUseCase(private val customerRepository: CustomerRepository) {
    suspend operator fun invoke(): Resource<ProductDetails> {
        return customerRepository.getProductDetails()
    }
}