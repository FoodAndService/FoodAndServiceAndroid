package com.foodandservice.data.remote.service

import com.foodandservice.data.remote.model.sign.NetworkAuthPhase
import com.foodandservice.data.remote.model.sign.NetworkPhaseWithAuth
import com.foodandservice.domain.model.CustomerPhone
import com.foodandservice.domain.model.Name
import com.foodandservice.domain.model.PhoneWithOtp
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.PUT

interface CustomerService {
    @PUT("customer/sign-in/first-phase")
    suspend fun signInFirstPhase(@Body customerPhone: CustomerPhone): NetworkAuthPhase

    @PUT("customer/sign-in/second-phase")
    suspend fun signInSecondPhase(@Body phoneWithOtp: PhoneWithOtp): NetworkPhaseWithAuth

    @PUT("customer/sign-up/first-phase")
    suspend fun signUpFirstPhase(
        @Header("Authorization") authorization: String,
        @Body name: Name
    ): NetworkPhaseWithAuth
}