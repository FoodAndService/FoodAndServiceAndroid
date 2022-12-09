package com.foodandservice.data.remote.model.sign

import com.foodandservice.domain.model.sign.AuthPhase

data class NetworkAuthPhase(val currentPhase: String)

fun NetworkAuthPhase.toAuthPhase(): AuthPhase = AuthPhase(currentPhase)