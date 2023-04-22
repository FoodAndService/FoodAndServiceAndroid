package com.foodandservice.domain.model.restaurant

import java.io.Serializable

data class Restaurant(
    val address: RestaurantAddress,
    val banner: String,
    val categoryId: String,
    val categoryName: String,
    val description: String,
    val id: String,
    val logo: String,
    val name: String,
    val openingStatus: RestaurantOpeningStatus,
    val phone: String,
    val schedule: List<RestaurantSchedule>?,
    var distanceInKm: Float = 0f
) {
    fun isOpen() = openingStatus == RestaurantOpeningStatus.OPEN
    fun isClosed() = openingStatus == RestaurantOpeningStatus.CLOSED
    fun isOnVacation() = openingStatus == RestaurantOpeningStatus.VACATION
}

enum class RestaurantOpeningStatus : Serializable {
    OPEN, CLOSED, VACATION, UNKNOWN;

    companion object {
        fun getStatus(openingStatus: String) = when (openingStatus) {
            "open" -> OPEN
            "closed" -> CLOSED
            "vacation" -> VACATION
            else -> UNKNOWN
        }
    }
}