package com.foodandservice.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
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
    private val viewModel by viewModels<HomeViewModel>()

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

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.homeState.collect { state ->
                when (state) {
                    is HomeState.Success -> {
                        categoryRestaurantsAdapter.submitList(state.restaurants)
                        categoryTagAdapter.submitList(state.categoryTags)
                    }
                    is HomeState.Error -> {
                        makeToast(state.message)
                    }
                    is HomeState.Loading -> {
                        TODO("Loading effect")
                    }
                    is HomeState.Empty -> {}
                }
            }
        }

        binding.btnCart.setOnClickListener {

        }
    }

    private fun makeToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
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
        val action = HomeFragmentDirections.actionHomeFragmentToRestaurantInfoFragment()
        findNavController().navigate(action)
    }
}