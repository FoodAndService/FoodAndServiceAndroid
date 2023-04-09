package com.foodandservice.domain.model

import java.io.Serializable

data class RestaurantDetails(
    val address: RestaurantDetailsAddress,
    val banner: String,
    val categoryId: String,
    val categoryName: String,
    val description: String,
    val id: String,
    val logo: String,
    val name: String,
    val openingStatus: RestaurantOpeningStatus,
    val phone: String,
    val schedule: List<RestaurantDetailsSchedule>
) : Serializable {
    fun isOpen() = openingStatus == RestaurantOpeningStatus.OPEN
    fun isClosed() = openingStatus == RestaurantOpeningStatus.CLOSED
    fun isOnVacation() = openingStatus == RestaurantOpeningStatus.VACATION
}

data class RestaurantDetailsAddress(
    val city: String,
    val country: String,
    val latitude: Double,
    val longitude: Double,
    val name: String,
    val number: Int,
    val postalCode: String
) : Serializable

data class RestaurantDetailsSchedule(
    val endHour: Int,
    val endMinutes: Int,
    val startHour: Int,
    val startMinutes: Int,
    val weekDay: Int
) : Serializable