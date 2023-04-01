package com.foodandservice.data.remote.model.sign

import com.foodandservice.domain.model.sign.AuthPhase

data class AuthPhaseDto(val currentPhase: String)

fun AuthPhaseDto.toAuthPhase(): AuthPhase = AuthPhase(currentPhase = currentPhase)