package com.foodandservice.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.foodandservice.data.local.entity.RestaurantCartProductEntity

@Dao
interface CartDao {
    @Query("SELECT * FROM RestaurantCartProductEntity")
    fun getAll(): List<RestaurantCartProductEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cartProduct: RestaurantCartProductEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cartProducts: List<RestaurantCartProductEntity>)

    @Update
    suspend fun update(cartProduct: RestaurantCartProductEntity)

    @Update
    suspend fun update(cartProducts: List<RestaurantCartProductEntity>)

    @Delete
    suspend fun delete(cartProduct: RestaurantCartProductEntity)

    @Delete
    suspend fun delete(cartProducts: List<RestaurantCartProductEntity>)
}