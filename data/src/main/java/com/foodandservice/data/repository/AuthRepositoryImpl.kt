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
import com.foodandservice.domain.util.Resource

class AuthRepositoryImpl(private val authService: AuthService) : AuthRepository {
    override suspend fun signInFirstPhase(customerPhone: CustomerPhone): Resource<AuthPhase> {
        return try {
            val response = authService.signInFirstPhase(customerPhone)
            Resource.Success(response.toAuthPhase())
        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }

    override suspend fun signInSecondPhase(phoneWithOtp: PhoneWithOtp): Resource<AuthPhaseWithToken> {
        return try {
            val response = authService.signInSecondPhase(phoneWithOtp)
            Resource.Success(response.toAuthPhaseWithToken())
        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }

    override suspend fun signUpFirstPhase(
        name: Name
    ): Resource<AuthPhaseWithToken> {
        return try {
            val response = authService.signUpFirstPhase(name)
            Resource.Success(response.toAuthPhaseWithToken())
        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }
}