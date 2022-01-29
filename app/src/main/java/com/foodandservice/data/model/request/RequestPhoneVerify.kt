package com.foodandservice.data.model.request

data class RequestPhoneVerify(
    val phone: String,
    val code: String
)