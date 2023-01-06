package com.foodandservice.data.repository

import com.foodandservice.data.remote.model.sign.toAuthPhase
import com.foodandservice.data.remote.model.sign.toSignPhase
import com.foodandservice.data.remote.service.CustomerService
import com.foodandservice.domain.model.CustomerPhone
import com.foodandservice.domain.model.Name
import com.foodandservice.domain.model.PhoneWithOtp
import com.foodandservice.domain.model.sign.AuthPhase
import com.foodandservice.domain.model.sign.AuthPhaseWithToken
import com.foodandservice.domain.repository.CustomerRepository
import com.foodandservice.domain.util.Resource

class CustomerRepositoryImpl(private val customerService: CustomerService) : CustomerRepository {
    override suspend fun signInFirstPhase(customerPhone: CustomerPhone): Resource<AuthPhase> {
        return try {
            val response = customerService.signInFirstPhase(customerPhone)
            Resource.Success(response.toAuthPhase())
        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }

    override suspend fun signInSecondPhase(phoneWithOtp: PhoneWithOtp): Resource<AuthPhaseWithToken> {
        return try {
            val response = customerService.signInSecondPhase(phoneWithOtp)
            Resource.Success(response.toSignPhase())
        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }

    override suspend fun signUpFirstPhase(
        authToken: String, name: Name
    ): Resource<AuthPhaseWithToken> {
        return try {
            val response = customerService.signUpFirstPhase("Bearer $authToken", name)
            Resource.Success(response.toSignPhase())
        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }
}