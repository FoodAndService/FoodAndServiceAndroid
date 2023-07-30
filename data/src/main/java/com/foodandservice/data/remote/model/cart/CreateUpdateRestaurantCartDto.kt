package com.foodandservice.data.remote.model.cart

data class CreateUpdateRestaurantCartDto(
    val businessId: String,
    val items: List<CreateUpdateRestaurantCartItemDto>
)

data class CreateUpdateRestaurantCartItemDto(
    val id: String,
    val productId: String,
    val quantity: Int,
    val note: String,
    val extras: List<CreateUpdateRestaurantCartExtraDto>
)

data class CreateUpdateRestaurantCartExtraDto(
    val productExtraId: String,
    val quantity: Int
)