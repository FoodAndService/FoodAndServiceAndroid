package com.foodandservice.presentation.ui.home_category_filter

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.foodandservice.domain.model.Restaurant

abstract class HomeCategoryFilterViewModel : ViewModel() {
    sealed class State

    abstract fun getState(): LiveData<State>
    abstract fun getRestaurantsByCategory(category: String): LiveData<List<Restaurant>>
}