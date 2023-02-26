package com.foodandservice.domain.usecases.stripe

import com.foodandservice.domain.model.stripe.PaymentInfo
import com.foodandservice.domain.model.stripe.PaymentIntentKey
import com.foodandservice.domain.repository.StripeRepository
import com.foodandservice.domain.util.Resource

class GetStripePaymentIntentKeyUseCase(private val stripeRepository: StripeRepository) {
    suspend operator fun invoke(
        secret: String, paymentInfo: PaymentInfo
    ): Resource<PaymentIntentKey> {
        return stripeRepository.getPaymentIntentKey(
            secret = secret, paymentInfo = paymentInfo
        )
    }
}