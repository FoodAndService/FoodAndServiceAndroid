package com.foodandservice.data.network

import com.foodandservice.data.model.request.RequestPhone
import com.foodandservice.data.model.request.RequestPhoneVerify
import com.foodandservice.data.model.response.Response
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header

interface RestaurantClientService {
    @GET("v1/auth/sms/send")
    fun smsSend(
        @Header("Authorization") authToken: String,
        @Body phone: RequestPhone
    ): Call<Response>

    @GET("v1/auth/sms/verify")
    fun smsVerify(
        @Header("Authorization") authToken: String,
        @Body phoneVerify: RequestPhoneVerify
    ): Call<Response>
}