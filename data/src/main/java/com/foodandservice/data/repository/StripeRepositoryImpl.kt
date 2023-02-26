package com.foodandservice.data.repository

import com.foodandservice.data.remote.model.stripe.toEphemeralKey
import com.foodandservice.data.remote.model.stripe.toPaymentIntentKey
import com.foodandservice.data.remote.service.StripeService
import com.foodandservice.domain.model.stripe.EphemeralKey
import com.foodandservice.domain.model.stripe.PaymentInfo
import com.foodandservice.domain.model.stripe.PaymentIntentKey
import com.foodandservice.domain.model.stripe.StripeCustomer
import com.foodandservice.domain.repository.StripeRepository
import com.foodandservice.domain.util.Resource

class StripeRepositoryImpl(private val stripeService: StripeService) : StripeRepository {
    override suspend fun getEphemeralKey(
        secret: String, stripeVersion: String, stripeCustomer: StripeCustomer
    ): Resource<EphemeralKey> {
        return try {
            val response = stripeService.getEphemeralKey(
                secret = secret, stripeVersion = stripeVersion, stripeCustomer = stripeCustomer
            )
            Resource.Success(response.toEphemeralKey())
        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }

    override suspend fun getPaymentIntentKey(
        secret: String, paymentInfo: PaymentInfo
    ): Resource<PaymentIntentKey> {
        return try {
            val response = stripeService.getPaymentIntent(
                secret = secret, paymentInfo = paymentInfo
            )
            Resource.Success(response.toPaymentIntentKey())
        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }

    override suspend fun getStripeCustomer(): Resource<String> {
        return try {
            Resource.Success("cus_NLRsVvpCow25yB")
        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }
}