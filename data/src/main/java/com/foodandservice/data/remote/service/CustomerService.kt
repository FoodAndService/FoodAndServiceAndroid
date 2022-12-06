package com.foodandservice.data.remote.service

import com.foodandservice.data.remote.model.sign.NetworPhaseWithAuth
import com.foodandservice.data.remote.model.sign.PhaseDto
import com.foodandservice.domain.model.Name
import com.foodandservice.domain.model.Phone
import com.foodandservice.domain.model.PhoneWithSmsCode
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.PUT

interface CustomerService {
    @PUT("customer/sign-in/first-phase")
    suspend fun signInFirstPhase(@Body phone: Phone): PhaseDto

    @PUT("customer/sign-in/second-phase")
    suspend fun signInSecondPhase(@Body phoneWithSmsCode: PhoneWithSmsCode): NetworPhaseWithAuth

    @PUT("customer/sign-up/first-phase")
    suspend fun signUpFirstPhase(
        @Header("Authorization") authorization: String,
        @Body name: Name
    ): NetworPhaseWithAuth
}