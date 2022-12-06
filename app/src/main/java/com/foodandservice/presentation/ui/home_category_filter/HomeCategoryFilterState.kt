package com.foodandservice.presentation.ui.home_category_filter

import com.foodandservice.domain.model.Restaurant

sealed class HomeCategoryFilterState {
    data class Success(
        val restaurants: List<Restaurant>
    ) : HomeCategoryFilterState()

    data class Error(val message: String) : HomeCategoryFilterState()
    object Loading : HomeCategoryFilterState()
    object Idle : HomeCategoryFilterState()
}
