package com.foodandservice.presentation.ui.home_category_filter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.foodandservice.R
import com.foodandservice.databinding.FragmentHomeCategoryFilterBinding
import com.foodandservice.domain.model.restaurant.Restaurant
import com.foodandservice.presentation.ui.adapter.RestaurantAdapter
import com.foodandservice.util.LocationUtils
import com.foodandservice.util.extensions.CoreExtensions.navigateBack
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get

class HomeCategoryFilterFragment : Fragment(), RestaurantAdapter.RestaurantClickListener {
    private lateinit var restaurantAdapter: RestaurantAdapter
    private lateinit var binding: FragmentHomeCategoryFilterBinding
    private val args: HomeCategoryFilterFragmentArgs by navArgs()
    private val viewModel: HomeCategoryFilterViewModel = get()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeCategoryFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.homeCategoryFilterState.collect { state ->
                    when (state) {
                        is HomeCategoryFilterState.Loading -> {
                            setLoadingState()
                        }

                        is HomeCategoryFilterState.Error -> {

                        }

                        is HomeCategoryFilterState.Idle -> {
                            setIdleState()
                        }
                    }
                }
            }
        }

        binding.apply {
            tvCategory.text = getString(R.string.category_filter_title, args.categoryName)

            btnBack.setOnClickListener {
                navigateBack()
            }
        }

        fetchRestaurants()
    }

    private fun fetchRestaurants() {
        try {
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.getCategoryRestaurants(
                    coordinate = LocationUtils.getUserCoordinates(),
                    restaurantCategoryId = args.categoryId
                ).collectLatest { restaurants ->
                    restaurantAdapter.submitData(restaurants)
                }
            }
        } catch (e: Exception) {
            Log.e("HomeFragment", e.message, e)
        }
    }

    private fun setLoadingState() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun setIdleState() {
        binding.progressBar.visibility = View.GONE
    }

    private fun setAdapter() {
        restaurantAdapter = RestaurantAdapter(this).also { adapter ->
            binding.rvRestaurant.adapter = adapter
        }
    }

    override fun onClick(restaurant: Restaurant) {

    }
}