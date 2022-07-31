package com.foodandservice.presentation.ui.home_category_filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.foodandservice.R
import com.foodandservice.databinding.FragmentHomeCategoryFilterBinding
import com.foodandservice.domain.model.Restaurant
import com.foodandservice.presentation.ui.adapter.RestaurantAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class HomeCategoryFilterFragment : Fragment(), RestaurantAdapter.RestaurantClickListener {
    private lateinit var binding: FragmentHomeCategoryFilterBinding
    private lateinit var restaurantAdapter: RestaurantAdapter
    private val args: HomeCategoryFilterFragmentArgs by navArgs()
    private val viewModel by viewModels<HomeCategoryFilterViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_category_filter, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()

        binding.btnCart.setOnClickListener {
            TODO("from home filter to cart")
        }

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        viewModel.getRestaurantsByCategory(args.category)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.homeCategoryFilterState.collect{ state ->
                when(state){
                    is HomeCategoryFilterState.Empty -> {}
                    is HomeCategoryFilterState.Success -> {
                        restaurantAdapter.submitList(state.restaurants)
                    }
                    is HomeCategoryFilterState.Loading -> {
                        TODO("Show loading")
                    }
                    is HomeCategoryFilterState.Error -> {
                        TODO("Error")
                    }
                }
            }
        }

        binding.tvCategory.text = args.category.uppercase()
    }

    private fun setAdapter() {
        restaurantAdapter = RestaurantAdapter(this)
        binding.rvRestaurant.adapter = restaurantAdapter
    }

    override fun onClick(item: Restaurant) {

    }
}