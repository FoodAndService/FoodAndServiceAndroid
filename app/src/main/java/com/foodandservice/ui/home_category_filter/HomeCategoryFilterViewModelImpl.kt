package com.foodandservice.ui.home_category_filter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.foodandservice.data.model.Restaurant

class HomeCategoryFilterViewModelImpl : HomeCategoryFilterViewModel() {
    private val restaurantList = MutableLiveData<List<Restaurant>>()
    private val state = MutableLiveData<State>()

    override fun getRestaurantsByCategory(category: String): LiveData<List<Restaurant>> {
        val list = listOf(
            Restaurant(
                "Rosario's Burger",
                2f,
                300,
            ),
            Restaurant(
                "Domino's Pizza",
                4f,
                200,
            ),
            Restaurant(
                "Five Guys",
                3f,
                900,
            ),
            Restaurant(
                "Five Guys",
                3f,
                900,
            ),
            Restaurant(
                "Five Guys",
                3f,
                900,
            )
        )

        restaurantList.value = list
        return restaurantList
    }

    override fun getState(): LiveData<State> {
        return state
    }
}