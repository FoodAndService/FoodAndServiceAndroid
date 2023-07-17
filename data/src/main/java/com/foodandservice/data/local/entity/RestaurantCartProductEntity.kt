package com.foodandservice.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class RestaurantCartProductEntity(
    @PrimaryKey val id: String,
    val productId: String,
    val quantity: Int,
    val extras: List<RestaurantCartProductExtraEntity>
)