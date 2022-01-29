package com.foodandservice.ui.home_category_filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.foodandservice.R
import com.foodandservice.data.model.Restaurant
import com.foodandservice.databinding.FragmentHomeCategoryFilterBinding
import com.foodandservice.ui.adapter.RestaurantAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeCategoryFilterFragment : Fragment(), RestaurantAdapter.RestaurantClickListener {
    private lateinit var binding: FragmentHomeCategoryFilterBinding
    private lateinit var restaurantAdapter: RestaurantAdapter
    private val args: HomeCategoryFilterFragmentArgs by navArgs()
    private val viewModel by viewModels<HomeCategoryFilterViewModelImpl>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home_category_filter,
            container,
            false
        )
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

        viewModel.getRestaurantsByCategory("cacota").observe(viewLifecycleOwner, {
            restaurantAdapter.submitList(it)
        })

        binding.tvCategory.text = args.category.uppercase()
    }

    private fun setAdapter() {
        restaurantAdapter = RestaurantAdapter(this)
        binding.rvRestaurant.adapter = restaurantAdapter
    }

    override fun onClick(item: Restaurant) {

    }
}