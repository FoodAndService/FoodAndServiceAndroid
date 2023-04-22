package com.foodandservice.domain.model.restaurant_details

import com.foodandservice.domain.model.restaurant.RestaurantAddress
import com.foodandservice.domain.model.restaurant.RestaurantOpeningStatus
import com.foodandservice.domain.model.restaurant.RestaurantSchedule
import java.io.Serializable

data class RestaurantDetails(
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
    val schedule: List<RestaurantSchedule>
) : Serializable {
    fun isOpen() = openingStatus == RestaurantOpeningStatus.OPEN
    fun isClosed() = openingStatus == RestaurantOpeningStatus.CLOSED
    fun isOnVacation() = openingStatus == RestaurantOpeningStatus.VACATION
}