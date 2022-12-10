package com.foodandservice.domain.model.sign

data class AuthPhaseWithToken(val currentPhase: AuthCurrentPhase, val authUser: String)

enum class AuthCurrentPhase {
    PHONE_VERIFIED,
    INFO_ADDED,
    UNKNOWN;

    companion object {
        fun from(currentPhase: String): AuthCurrentPhase {
            return when (currentPhase) {
                "phone_verified" -> PHONE_VERIFIED
                "info_added" -> INFO_ADDED
                else -> UNKNOWN
            }
        }
    }
}