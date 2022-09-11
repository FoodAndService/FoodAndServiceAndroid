package com.foodandservice.data.remote.model.sign

import com.foodandservice.domain.model.sign.PhaseWithAuth

data class NetworPhaseWithAuth(val currentPhase: String, val authUser: String)

fun NetworPhaseWithAuth.toSignPhase(): PhaseWithAuth = PhaseWithAuth(currentPhase, authUser)