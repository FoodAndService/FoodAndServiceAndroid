package com.foodandservice.domain.repository

import com.foodandservice.domain.model.stripe.EphemeralKey
import com.foodandservice.domain.model.stripe.PaymentInfo
import com.foodandservice.domain.model.stripe.PaymentIntentKey
import com.foodandservice.domain.model.stripe.StripeCustomer
import com.foodandservice.domain.util.ApiResponse

interface StripeRepository {
    suspend fun getEphemeralKey(
        secret: String,
        stripeVersion: String,
        stripeCustomer: StripeCustomer
    ): ApiResponse<EphemeralKey>

    suspend fun getPaymentIntentKey(
        secret: String,
        paymentInfo: PaymentInfo
    ): ApiResponse<PaymentIntentKey>

    suspend fun getStripeCustomer(): ApiResponse<String>
}