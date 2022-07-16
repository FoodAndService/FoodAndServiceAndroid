package com.foodandservice.domain.model

data class RequestPhoneVerify(
    val phone: String,
    val code: String
)