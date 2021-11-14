package com.foodandservice.data.model

import com.google.gson.annotations.SerializedName

data class CategoryRestaurants(
    @SerializedName("_id")
    val id: Int,

    @SerializedName("_name")
    val name: String,

    @SerializedName("_restaurants")
    val restaurants: List<Restaurant>
)