package com.foodandservice.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.foodandservice.R
import com.foodandservice.databinding.FragmentHomeBinding
import com.foodandservice.domain.model.CategoryTag
import com.foodandservice.domain.model.Restaurant
import com.foodandservice.presentation.ui.adapter.CategoryRestaurantsAdapter
import com.foodandservice.presentation.ui.adapter.CategoryTagAdapter
import com.foodandservice.presentation.ui.adapter.RestaurantAdapter
import com.foodandservice.util.FysBottomSheets.showHomeFilterBottomSheet
import com.foodandservice.util.RecyclerViewItemDecoration
import com.foodandservice.util.extensions.CoreExtensions.showToast
import org.koin.android.ext.android.get

class HomeFragment : Fragment(), RestaurantAdapter.RestaurantClickListener,
    CategoryTagAdapter.CategoryTagClickListener {
    private lateinit var categoryRestaurantsAdapter: CategoryRestaurantsAdapter
    private lateinit var categoryTagAdapter: CategoryTagAdapter
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel = get()

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
                        showToast(state.message)
                    }
                    is HomeState.Loading -> {

                    }
                    is HomeState.Idle -> {

                    }
                }
            }
        }

        binding.apply {
            btnCart.setOnClickListener {
                navigateToCart()
            }

            btnFilter.setOnClickListener {
                showHomeFilterBottomSheet(
                    layout = R.layout.bottom_sheet_home_filter,
                    onBtnRecommendedClick = {

                    },
                    onBtnPopularClick = {

                    },
                    onBtnValoratedClick = {

                    },
                    onBtnPriceLowClick = {

                    },
                    onBtnPriceMediumClick = {

                    },
                    onBtnPriceHighClick = {

                    }
                )
            }
        }

        requireActivity()
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner) {
                requireActivity().moveTaskToBack(true)
            }
    }

    private fun navigateToCart() {
        findNavController().navigate(R.id.action_homeFragment_to_cartFragment)
    }

    private fun setAdapters() {
        categoryRestaurantsAdapter = CategoryRestaurantsAdapter(this)
        categoryTagAdapter = CategoryTagAdapter(this)

        binding.apply {
            rvCategoriesWithRestaurants.adapter = categoryRestaurantsAdapter
            rvCategoryTag.adapter = categoryTagAdapter
            rvCategoryTag.addItemDecoration(RecyclerViewItemDecoration(24))
        }
    }

    override fun onClick(categoryTag: CategoryTag) {
        val action =
            HomeFragmentDirections.actionHomeFragmentToHomeCategoryFilterFragment(categoryTag.name)
        findNavController().navigate(action)
    }

    override fun onClick(item: Restaurant) {
        val action = HomeFragmentDirections.actionHomeFragmentToRestaurantDetailsFragment()
        findNavController().navigate(action)
    }
}