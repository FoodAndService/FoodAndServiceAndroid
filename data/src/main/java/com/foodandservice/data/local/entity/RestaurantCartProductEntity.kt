package com.foodandservice.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "restaurant_cart_products")
data class RestaurantCartProductEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "productId") val productId: String,
    @ColumnInfo(name = "quantity") var quantity: Int,
    @ColumnInfo(name = "note") val note: String
)