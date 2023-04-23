package com.foodandservice.util

import com.foodandservice.domain.model.restaurant_details.RestaurantProductCategoryWithProducts

fun getProductCategoriesIndices(categoriesWithProducts: List<RestaurantProductCategoryWithProducts>): List<Int> {
    val indices = mutableListOf<Int>()
    var currentIndex = 0

    for (categoryWithProducts in categoriesWithProducts) {
        if (categoryWithProducts.products.isNotEmpty()) {
            indices.add(currentIndex)
            currentIndex += categoryWithProducts.products.size
        } else {
            currentIndex++
        }
    }

    return indices
}