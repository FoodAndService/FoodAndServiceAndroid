package com.foodandservice.domain.model

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
    val schedule: List<RestaurantSchedule>?
) {
    fun isOpen() = openingStatus == RestaurantOpeningStatus.OPEN
    fun isClosed() = openingStatus == RestaurantOpeningStatus.CLOSED
    fun isOnVacation() = openingStatus == RestaurantOpeningStatus.VACATION
}

data class RestaurantAddress(
    val city: String,
    val country: String,
    val latitude: Double,
    val longitude: Double,
    val name: String,
    val number: Int,
    val postalCode: String
)

data class RestaurantSchedule(
    val endHour: Int,
    val endMinutes: Int,
    val startHour: Int,
    val startMinutes: Int,
    val weekDay: Int
)

enum class RestaurantOpeningStatus {
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