package com.foodandservice.data.repository

import com.foodandservice.data.model.request.RequestPhone
import com.foodandservice.data.model.request.RequestPhoneVerify
import com.foodandservice.data.model.response.Response
import com.foodandservice.data.network.RestaurantClientService
import retrofit2.await
import javax.inject.Inject

class RestaurantClientRepository @Inject constructor(private val service: RestaurantClientService) {
    suspend fun smsSend(authToken: String, phone: String): Result<Response> {
        return try {
            val result = service.smsSend(authToken, RequestPhone(phone)).await()
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun smsVerify(authToken: String, phone: String, code: String): Result<Response> {
        return try {
            val result = service.smsVerify(authToken, RequestPhoneVerify(phone, code)).await()
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}