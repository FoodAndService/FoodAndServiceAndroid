package com.foodandservice.domain.repository

import com.foodandservice.domain.model.Name
import com.foodandservice.domain.model.Phone
import com.foodandservice.domain.model.PhoneWithSmsCode
import com.foodandservice.domain.model.sign.Phase
import com.foodandservice.domain.model.sign.PhaseWithAuth
import com.foodandservice.domain.util.Resource

interface CustomerRepository {
    suspend fun signInFirstPhase(phone: Phone): Resource<Phase>
    suspend fun signInSecondPhase(phoneWithSmsCode: PhoneWithSmsCode): Resource<PhaseWithAuth>
    suspend fun signUpFirstPhase(authToken: String, name: Name): Resource<PhaseWithAuth>
}