package com.foodandservice.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.foodandservice.R
import com.foodandservice.databinding.FragmentHomeBinding
import com.foodandservice.domain.model.CategoryTag
import com.foodandservice.domain.model.Restaurant
import com.foodandservice.presentation.ui.adapter.CategoryRestaurantsAdapter
import com.foodandservice.presentation.ui.adapter.CategoryTagAdapter
import com.foodandservice.presentation.ui.adapter.RestaurantAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), RestaurantAdapter.RestaurantClickListener,
    CategoryTagAdapter.CategoryTagClickListener {
    private lateinit var categoryRestaurantsAdapter: CategoryRestaurantsAdapter
    private lateinit var categoryTagAdapter: CategoryTagAdapter
    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<HomeViewModelImpl>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapters()

        viewModel.getState().observe(viewLifecycleOwner) {

        }

        viewModel.getCategoryTagList().observe(viewLifecycleOwner) {
            categoryTagAdapter.submitList(it)
        }

        viewModel.getRestaurantList().observe(viewLifecycleOwner) {
            categoryRestaurantsAdapter.submitList(it)
        }

        binding.btnCart.setOnClickListener {

        }
    }

    private fun setAdapters() {
        categoryRestaurantsAdapter = CategoryRestaurantsAdapter(this)
        categoryTagAdapter = CategoryTagAdapter(this)

        binding.rvCategoriesWithRestaurants.adapter = categoryRestaurantsAdapter
        binding.rvCategoryTag.adapter = categoryTagAdapter
    }

    override fun onClick(categoryTag: CategoryTag) {
        val action =
            HomeFragmentDirections.actionHomeFragmentToHomeCategoryFilterFragment(categoryTag.name)
        findNavController().navigate(action)
    }

    override fun onClick(item: Restaurant) {

    }
}