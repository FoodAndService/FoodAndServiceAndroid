package com.foodandservice.data.repository

import com.foodandservice.data.remote.model.sign.toAuthPhase
import com.foodandservice.data.remote.model.sign.toAuthPhaseWithToken
import com.foodandservice.data.remote.service.AuthService
import com.foodandservice.domain.model.CustomerPhone
import com.foodandservice.domain.model.Name
import com.foodandservice.domain.model.PhoneWithOtp
import com.foodandservice.domain.model.sign.AuthPhase
import com.foodandservice.domain.model.sign.AuthPhaseWithToken
import com.foodandservice.domain.repository.AuthRepository
import com.foodandservice.domain.util.ApiResponse

class AuthRepositoryImpl(private val authService: AuthService) : AuthRepository {
    override suspend fun signInFirstPhase(customerPhone: CustomerPhone): ApiResponse<AuthPhase> {
        return try {
            val response = authService.signInFirstPhase(customerPhone)
            ApiResponse.Success(response.toAuthPhase())
        } catch (exception: Exception) {
            ApiResponse.Failure(exception)
        }
    }

    override suspend fun signInSecondPhase(phoneWithOtp: PhoneWithOtp): ApiResponse<AuthPhaseWithToken> {
        return try {
            val response = authService.signInSecondPhase(phoneWithOtp)
            ApiResponse.Success(response.toAuthPhaseWithToken())
        } catch (exception: Exception) {
            ApiResponse.Failure(exception)
        }
    }

    override suspend fun signUpFirstPhase(
        name: Name
    ): ApiResponse<AuthPhaseWithToken> {
        return try {
            val response = authService.signUpFirstPhase(name)
            ApiResponse.Success(response.toAuthPhaseWithToken())
        } catch (exception: Exception) {
            ApiResponse.Failure(exception)
        }
    }
}