package com.foodandservice.data.remote.model.auth

import com.foodandservice.domain.model.auth.AuthCurrentPhase
import com.foodandservice.domain.model.auth.AuthPhaseWithToken

data class AuthPhaseWithTokenDto(val currentPhase: String, val authUser: String)

fun AuthPhaseWithTokenDto.toAuthPhaseWithToken() =
    AuthPhaseWithToken(currentPhase = AuthCurrentPhase.from(currentPhase), token = authUser)
