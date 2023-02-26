package com.foodandservice.data.remote.model.sign.response

import com.foodandservice.domain.model.sign.AuthCurrentPhase
import com.foodandservice.domain.model.sign.AuthPhaseWithToken

data class AuthPhaseWithTokenDto(val currentPhase: String, val authUser: String)

fun AuthPhaseWithTokenDto.toAuthPhaseWithToken(): AuthPhaseWithToken =
    AuthPhaseWithToken(currentPhase = AuthCurrentPhase.from(currentPhase), token = authUser)