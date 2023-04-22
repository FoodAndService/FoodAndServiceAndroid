package com.foodandservice.presentation.ui.restaurant_booking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.foodandservice.R
import com.foodandservice.databinding.FragmentRestaurantBookingBinding
import com.foodandservice.util.extensions.CoreExtensions.navigateBack
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get

class RestaurantBookingFragment : Fragment() {
    private lateinit var binding: FragmentRestaurantBookingBinding
    private val viewModel: RestaurantBookingViewModel = get()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentRestaurantBookingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.restaurantBookingState.collect { state ->
                    when (state) {
                        is RestaurantBookingState.Success -> {

                        }

                        is RestaurantBookingState.Loading -> {
                            setLoadingState()
                        }

                        is RestaurantBookingState.Error -> {

                        }

                        is RestaurantBookingState.Idle -> {
                            setIdleState()
                        }
                    }
                }
            }
        }

        binding.apply {
            btnBack.setOnClickListener {
                navigateBack()
            }

            tvRestaurantName.text = getString(R.string.tv_table_booking_restaurant, "Wendy's")
        }
    }

    private fun setIdleState() {

    }

    private fun setLoadingState() {

    }
}