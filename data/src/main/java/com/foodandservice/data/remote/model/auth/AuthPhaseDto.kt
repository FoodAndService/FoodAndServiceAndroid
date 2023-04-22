package com.foodandservice.data.remote.model.auth

import com.foodandservice.domain.model.auth.AuthPhase

data class AuthPhaseDto(val currentPhase: String)

fun AuthPhaseDto.toAuthPhase() = AuthPhase(currentPhase = currentPhase)