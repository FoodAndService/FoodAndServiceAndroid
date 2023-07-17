package com.foodandservice.data.local.converters

import androidx.room.TypeConverter
import com.foodandservice.data.local.entity.RestaurantCartProductExtraEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RestaurantCartProductExtraConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromExtras(extras: List<RestaurantCartProductExtraEntity>?): String? {
        return gson.toJson(extras)
    }

    @TypeConverter
    fun toExtras(extrasString: String?): List<RestaurantCartProductExtraEntity>? {
        if (extrasString.isNullOrEmpty()) {
            return null
        }
        val type = object : TypeToken<List<RestaurantCartProductExtraEntity>>() {}.type
        return gson.fromJson(extrasString, type)
    }
}