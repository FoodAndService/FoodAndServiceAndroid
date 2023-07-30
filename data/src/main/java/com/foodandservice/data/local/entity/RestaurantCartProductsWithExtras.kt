package com.foodandservice.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation

class RestaurantCartProductsWithExtras {
    @Embedded
    lateinit var product: RestaurantCartProductEntity

    @Relation(
        parentColumn = "id",
        entityColumn = "cartItemId"
    )
    var extras: List<RestaurantCartProductExtraEntity> = emptyList()
}