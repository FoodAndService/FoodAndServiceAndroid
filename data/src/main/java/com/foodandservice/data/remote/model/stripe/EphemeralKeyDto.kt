package com.foodandservice.data.remote.model.stripe

import com.foodandservice.domain.model.stripe.EphemeralKey

data class EphemeralKeyDto(
    val associated_objects: List<AssociatedObjectDto>,
    val created: Int,
    val expires: Int,
    val id: String,
    val livemode: Boolean,
    val `object`: String,
    val secret: String
)

data class AssociatedObjectDto(
    val id: String,
    val type: String
)

fun EphemeralKeyDto.toEphemeralKey() = EphemeralKey(ephemeralKey = secret)