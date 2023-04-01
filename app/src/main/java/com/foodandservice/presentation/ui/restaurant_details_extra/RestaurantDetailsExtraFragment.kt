package com.foodandservice.presentation.ui.restaurant_details_extra

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.foodandservice.databinding.FragmentRestaurantDetailsExtraBinding
import com.foodandservice.domain.model.RestaurantDetailsExtra
import com.foodandservice.util.extensions.CoreExtensions.navigateBack
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get

class RestaurantDetailsExtraFragment : Fragment() {
    private lateinit var binding: FragmentRestaurantDetailsExtraBinding
    private val viewModel: RestaurantDetailsExtraViewModel = get()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentRestaurantDetailsExtraBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.restaurantDetailsExtraState.collect { state ->
                    when (state) {
                        is RestaurantDetailsExtraState.Success -> {
                            setDetails(restaurantDetailsExtra = state.restaurantDetailsExtra)
                        }
                        is RestaurantDetailsExtraState.Loading -> {
                            setLoadingState()
                        }
                        is RestaurantDetailsExtraState.Error -> {

                        }
                        is RestaurantDetailsExtraState.Idle -> {
                            setIdleState()
                        }
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

            }
        }

        binding.apply {
            btnBack.setOnClickListener {
                navigateBack()
            }
        }
    }

    private fun setIdleState() {

    }

    private fun setLoadingState() {

    }

    private fun setDetails(restaurantDetailsExtra: RestaurantDetailsExtra) {
        binding.tvSchedule.text = restaurantDetailsExtra.schedule
    }
}