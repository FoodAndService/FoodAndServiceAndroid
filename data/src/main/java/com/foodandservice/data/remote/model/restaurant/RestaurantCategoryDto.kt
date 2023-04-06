package com.foodandservice.data.remote.model.restaurant

import com.foodandservice.domain.model.RestaurantCategory

data class RestaurantCategoryDto(val id: String, val name: String)

fun RestaurantCategoryDto.toRestaurantCategory() = RestaurantCategory(id = id, name = name)