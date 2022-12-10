package com.foodandservice.data.remote.model.sign

import com.foodandservice.domain.model.sign.AuthCurrentPhase
import com.foodandservice.domain.model.sign.AuthPhaseWithToken

data class NetworkPhaseWithAuth(val currentPhase: String, val authUser: String)

fun NetworkPhaseWithAuth.toSignPhase(): AuthPhaseWithToken =
    AuthPhaseWithToken(currentPhase = AuthCurrentPhase.from(currentPhase), authUser = authUser)