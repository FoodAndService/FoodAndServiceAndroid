package com.foodandservice.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.foodandservice.data.local.converters.RestaurantCartProductExtraConverter
import com.foodandservice.data.local.dao.CartDao
import com.foodandservice.data.local.entity.RestaurantCartProductEntity
import com.foodandservice.data.local.entity.RestaurantCartProductExtraEntity

@Database(
    entities = [RestaurantCartProductEntity::class, RestaurantCartProductExtraEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    RestaurantCartProductExtraConverter::class
)
abstract class FysDatabase : RoomDatabase() {
    abstract fun cartDao(): CartDao
}