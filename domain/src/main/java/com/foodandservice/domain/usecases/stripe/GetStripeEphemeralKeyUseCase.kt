package com.foodandservice.domain.usecases.stripe

import com.foodandservice.domain.model.stripe.EphemeralKey
import com.foodandservice.domain.model.stripe.StripeCustomer
import com.foodandservice.domain.repository.StripeRepository
import com.foodandservice.domain.util.Resource

class GetStripeEphemeralKeyUseCase(private val stripeRepository: StripeRepository) {
    suspend operator fun invoke(
        secret: String, stripeVersion: String, stripeCustomer: StripeCustomer
    ): Resource<EphemeralKey> {
        return stripeRepository.getEphemeralKey(
            secret = secret, stripeVersion = stripeVersion, stripeCustomer = stripeCustomer
        )
    }
}