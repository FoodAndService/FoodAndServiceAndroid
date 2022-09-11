package com.foodandservice.util

import com.foodandservice.domain.model.CategoryWithProducts

fun getTabbedListMediatorIndices(list: List<CategoryWithProducts>): List<Int> {
    val indices = mutableListOf<Int>()

    list.forEachIndexed { index, categoryWithProducts ->
        if(index == 0)
            indices.add(index * categoryWithProducts.products.size)
        else
            indices.add(index * categoryWithProducts.products.size + 1)
    }

    return indices
}