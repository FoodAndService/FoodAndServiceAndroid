package com.foodandservice.domain.model.stripe

data class PaymentInfo(
    val customer: String,
    val amount: String,
    val currency: String
)
