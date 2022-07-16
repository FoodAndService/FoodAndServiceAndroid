package com.foodandservice.data.remote

import com.foodandservice.domain.model.RequestPhone
import com.foodandservice.domain.model.RequestPhoneVerify
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header

interface ClientApi {
    @GET("v1/auth/sms/send")
    fun smsSend(@Header("Authorization") authToken: String, @Body phone: RequestPhone): String

    @GET("v1/auth/sms/verify")
    fun smsVerify(
        @Header("Authorization") authToken: String,
        @Body phoneVerify: RequestPhoneVerify
    ): String
}