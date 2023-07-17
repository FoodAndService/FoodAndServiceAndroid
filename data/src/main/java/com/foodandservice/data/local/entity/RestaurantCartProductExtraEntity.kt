package com.foodandservice.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class RestaurantCartProductExtraEntity(
    @PrimaryKey val productExtraId: String,
    val quantity: Int
)