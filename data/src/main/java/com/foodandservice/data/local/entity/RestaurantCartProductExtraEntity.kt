package com.foodandservice.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "restaurant_cart_product_extras",
    primaryKeys = ["productExtraId", "cartItemId"],
    indices = [Index("cartItemId")],
    foreignKeys = [ForeignKey(
        entity = RestaurantCartProductEntity::class,
        parentColumns = ["id"],
        childColumns = ["cartItemId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class RestaurantCartProductExtraEntity(
    @ColumnInfo(name = "productExtraId") val productExtraId: String,
    @ColumnInfo(name = "cartItemId") val cartItemId: String,
    @ColumnInfo(name = "quantity") val quantity: Int
)