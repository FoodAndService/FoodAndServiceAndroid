package com.foodandservice.data.remote.model.sign

import com.foodandservice.domain.model.sign.Phase

data class PhaseDto(val currentPhase: String)

fun PhaseDto.toPhase(): Phase = Phase(currentPhase)