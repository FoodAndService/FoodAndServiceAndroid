package com.foodandservice.domain.repository

import com.foodandservice.domain.model.CustomerPhone
import com.foodandservice.domain.model.Name
import com.foodandservice.domain.model.PhoneWithOtp
import com.foodandservice.domain.model.sign.AuthPhase
import com.foodandservice.domain.model.sign.AuthPhaseWithToken
import com.foodandservice.domain.util.Resource

interface AuthRepository {
    suspend fun signInFirstPhase(customerPhone: CustomerPhone): Resource<AuthPhase>
    suspend fun signInSecondPhase(phoneWithOtp: PhoneWithOtp): Resource<AuthPhaseWithToken>
    suspend fun signUpFirstPhase(name: Name): Resource<AuthPhaseWithToken>
}