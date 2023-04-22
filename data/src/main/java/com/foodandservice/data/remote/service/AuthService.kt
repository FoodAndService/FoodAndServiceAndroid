package com.foodandservice.data.remote.service

import com.foodandservice.data.remote.model.auth.AuthPhaseDto
import com.foodandservice.data.remote.model.auth.AuthPhaseWithTokenDto
import com.foodandservice.domain.model.CustomerPhone
import com.foodandservice.domain.model.Name
import com.foodandservice.domain.model.PhoneWithOtp
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.PUT

interface AuthService {
    @PUT("customer/sign-in/first-phase")
    suspend fun signInFirstPhase(@Body customerPhone: CustomerPhone): AuthPhaseDto

    @PUT("customer/sign-in/second-phase")
    suspend fun signInSecondPhase(@Body phoneWithOtp: PhoneWithOtp): AuthPhaseWithTokenDto

    @PUT("customer/sign-up/first-phase")
    suspend fun signUpFirstPhase(
        @Body name: Name
    ): AuthPhaseWithTokenDto

    @PUT("customer/resend-sms")
    suspend fun resendSms(@Body phone: CustomerPhone): Response<Unit>
}