package com.foodandservice.presentation.ui.home_category_filter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodandservice.domain.model.Restaurant
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class HomeCategoryFilterViewModel : ViewModel() {
    private val _homeCategoryFilterState = MutableSharedFlow<HomeCategoryFilterState>(replay = 10)
    val homeCategoryFilterState: SharedFlow<HomeCategoryFilterState> =
        _homeCategoryFilterState.asSharedFlow()

    fun getRestaurantsByCategory(category: String) {
        viewModelScope.launch {
            _homeCategoryFilterState.emit(HomeCategoryFilterState.Loading)

            val restaurants = listOf(
                Restaurant(
                    id = "1",
                    name = "Rosario's Burger",
                    rating = 2f,
                    distance = 300,
                ), Restaurant(
                    id = "2",
                    name = "Domino's Pizza",
                    rating = 4f,
                    distance = 200,
                ), Restaurant(
                    id = "3",
                    name = "Five Guys",
                    rating = 3f,
                    distance = 900,
                )
            )

            _homeCategoryFilterState.emit(HomeCategoryFilterState.Success(restaurants = restaurants))

            _homeCategoryFilterState.emit(HomeCategoryFilterState.Idle)
        }
    }
}