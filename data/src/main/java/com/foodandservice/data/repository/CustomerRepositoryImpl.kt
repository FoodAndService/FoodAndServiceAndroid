package com.foodandservice.data.repository

import com.foodandservice.data.remote.model.sign.toPhase
import com.foodandservice.data.remote.model.sign.toSignPhase
import com.foodandservice.data.remote.service.CustomerService
import com.foodandservice.domain.model.Name
import com.foodandservice.domain.model.Phone
import com.foodandservice.domain.model.PhoneWithSmsCode
import com.foodandservice.domain.model.sign.Phase
import com.foodandservice.domain.model.sign.PhaseWithAuth
import com.foodandservice.domain.repository.CustomerRepository
import com.foodandservice.domain.util.Resource
import retrofit2.HttpException
import java.io.IOException

class CustomerRepositoryImpl(private val customerService: CustomerService) :
    CustomerRepository {
    override suspend fun signInFirstPhase(phone: Phone): Resource<Phase> {
        return try {
            val response = customerService.signInFirstPhase(phone)
            Resource.Success(response.toPhase())
        } catch (e: HttpException) {
            Resource.Error(e.localizedMessage ?: "Unexpected error ocurred")
        } catch (e: IOException) {
            Resource.Error("Couldn't reach the server. Check your internet connection")
        }
    }

    override suspend fun signInSecondPhase(phoneWithSmsCode: PhoneWithSmsCode): Resource<PhaseWithAuth> {
        return try {
            val response = customerService.signInSecondPhase(phoneWithSmsCode)
            Resource.Success(response.toSignPhase())
        } catch (e: HttpException) {
            Resource.Error(e.localizedMessage ?: "Unexpected error ocurred")
        } catch (e: IOException) {
            Resource.Error("Couldn't reach the server. Check your internet connection")
        }
    }

    override suspend fun signUpFirstPhase(authToken: String, name: Name): Resource<PhaseWithAuth> {
        return try {
            val response = customerService.signUpFirstPhase("Bearer $authToken", name)
            Resource.Success(response.toSignPhase())
        } catch (e: HttpException) {
            Resource.Error(e.localizedMessage ?: "Unexpected error ocurred")
        } catch (e: IOException) {
            Resource.Error("Couldn't reach the server. Check your internet connection")
        }
    }
}