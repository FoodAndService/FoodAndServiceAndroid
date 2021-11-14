package com.foodandservice.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.foodandservice.FoodAndServiceActivity
import com.foodandservice.R
import com.foodandservice.data.model.CategoryTag
import com.foodandservice.data.model.Restaurant
import com.foodandservice.databinding.FragmentHomeBinding
import com.foodandservice.ui.adapter.CategoryRestaurantsAdapter
import com.foodandservice.ui.adapter.CategoryTagAdapter
import com.foodandservice.ui.adapter.RestaurantAdapter

class HomeFragment : Fragment(), RestaurantAdapter.RestaurantClickListener,
    CategoryTagAdapter.CategoryTagClickListener {
    private lateinit var categoryRestaurantsAdapter: CategoryRestaurantsAdapter
    private lateinit var categoryTagAdapter: CategoryTagAdapter
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModelImpl by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showBottomBar()
        setAdapters()

        viewModel.getState().observe(viewLifecycleOwner, {

        })

        viewModel.getCategoryTagList().observe(viewLifecycleOwner, {
            categoryTagAdapter.submitList(it)
        })

        viewModel.getRestaurantList().observe(viewLifecycleOwner, {
            categoryRestaurantsAdapter.submitList(it)
        })

        binding.btnCart.setOnClickListener {

        }
    }

    private fun setAdapters() {
        categoryRestaurantsAdapter = CategoryRestaurantsAdapter(this)
        categoryTagAdapter = CategoryTagAdapter(this)

        binding.rvCategoriesWithRestaurants.adapter = categoryRestaurantsAdapter
        binding.rvCategoryTag.adapter = categoryTagAdapter
    }

    private fun showBottomBar() {
        val activity: FoodAndServiceActivity = activity as FoodAndServiceActivity
        activity.bottomBarVisibility(View.VISIBLE)
    }

    override fun onClick(categoryTag: CategoryTag) {

    }

    override fun onClick(item: Restaurant) {

    }
}