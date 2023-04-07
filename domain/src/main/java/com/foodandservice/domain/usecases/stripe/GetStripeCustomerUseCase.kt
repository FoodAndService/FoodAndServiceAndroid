package com.foodandservice.domain.usecases.stripe

import com.foodandservice.domain.repository.StripeRepository
import com.foodandservice.domain.util.ApiResponse

class GetStripeCustomerUseCase(private val stripeRepository: StripeRepository) {
    suspend operator fun invoke(): ApiResponse<String> {
        return stripeRepository.getStripeCustomer()
    }
}