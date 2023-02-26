package com.foodandservice.data.remote.service

import com.foodandservice.data.remote.model.stripe.EphemeralKeyDto
import com.foodandservice.data.remote.model.stripe.PaymentIntentDto
import com.foodandservice.domain.model.stripe.PaymentInfo
import com.foodandservice.domain.model.stripe.StripeCustomer
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface StripeService {
    @POST("v1/ephemeral_keys")
    suspend fun getEphemeralKey(
        @Header("Authorization") secret: String,
        @Header("Stripe-Version") stripeVersion: String,
        @Body stripeCustomer: StripeCustomer
    ): EphemeralKeyDto

    @POST("v1/payment_intents")
    suspend fun getPaymentIntent(
        @Header("Authorization") secret: String,
        @Body paymentInfo: PaymentInfo
    ): PaymentIntentDto
}