package com.foodandservice.domain.repository

import com.foodandservice.domain.model.CustomerPhone
import com.foodandservice.domain.model.Name
import com.foodandservice.domain.model.PhoneWithOtp
import com.foodandservice.domain.model.auth.AuthPhase
import com.foodandservice.domain.model.auth.AuthPhaseWithToken
import com.foodandservice.domain.util.ApiResponse

interface AuthRepository {
    suspend fun signInFirstPhase(customerPhone: CustomerPhone): ApiResponse<AuthPhase>
    suspend fun signInSecondPhase(phoneWithOtp: PhoneWithOtp): ApiResponse<AuthPhaseWithToken>
    suspend fun signUpFirstPhase(name: Name): ApiResponse<AuthPhaseWithToken>
    suspend fun resendSms(customerPhone: CustomerPhone): Boolean
}