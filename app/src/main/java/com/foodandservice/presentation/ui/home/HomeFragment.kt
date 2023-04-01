package com.foodandservice.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.foodandservice.R
import com.foodandservice.databinding.FragmentHomeBinding
import com.foodandservice.domain.model.Restaurant
import com.foodandservice.domain.model.RestaurantCategoryTag
import com.foodandservice.presentation.ui.adapter.CategoryTagAdapter
import com.foodandservice.presentation.ui.adapter.RestaurantAdapter
import com.foodandservice.util.FysBottomSheets.showHomeFilterBottomSheet
import com.foodandservice.util.RecyclerViewItemDecoration
import com.foodandservice.util.extensions.CoreExtensions.navigate
import com.foodandservice.util.extensions.CoreExtensions.showToast
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get

class HomeFragment : Fragment(), RestaurantAdapter.RestaurantClickListener,
    CategoryTagAdapter.CategoryTagClickListener {
    private lateinit var restaurantAdapter: RestaurantAdapter
    private lateinit var categoryTagAdapter: CategoryTagAdapter
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel = get()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapters()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.homeState.collect { state ->
                    when (state) {
                        is HomeState.Loading -> {
                            setLoadingState()
                        }
                        is HomeState.Error -> {
                            showToast(state.message)
                        }
                        is HomeState.Idle -> {
                            setIdleState()
                        }
                        else -> Unit
                    }
                }
            }
        }

        binding.apply {
            btnFilter.setOnClickListener {
                showHomeFilterBottomSheet(layout = R.layout.bottom_sheet_home_filter,
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

                    })
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            requireActivity().moveTaskToBack(true)
        }

        collectRestaurants()
    }

    private fun collectRestaurants() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getRestaurants().collectLatest { restaurants ->
                restaurantAdapter.submitData(restaurants)
            }
        }
    }

    private fun setAdapters() {
        restaurantAdapter = RestaurantAdapter(this)
        categoryTagAdapter = CategoryTagAdapter(this)

        binding.apply {
            rvRestaurant.apply {
                adapter = restaurantAdapter
                addItemDecoration(RecyclerViewItemDecoration(topMargin = 48, bottomMargin = 68))
            }
            rvCategoryTag.apply {
                adapter = categoryTagAdapter
                addItemDecoration(RecyclerViewItemDecoration(leftMargin = 48))
            }
        }
    }

    private fun setLoadingState() {

    }

    private fun setIdleState() {

    }

    override fun onClick(restaurantCategoryTag: RestaurantCategoryTag) {
        navigate(
            HomeFragmentDirections.actionHomeFragmentToHomeCategoryFilterFragment(
                restaurantCategoryTag.name
            )
        )
    }

    override fun onClick(item: Restaurant) {
        navigate(HomeFragmentDirections.actionHomeFragmentToRestaurantDetailsFragment())
    }
}