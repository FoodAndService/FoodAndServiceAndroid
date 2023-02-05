package com.foodandservice.presentation.ui.restaurant_details_extra

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.foodandservice.R
import com.foodandservice.databinding.FragmentRestaurantDetailsExtraBinding
import com.foodandservice.domain.model.RestaurantDetailsExtra
import com.foodandservice.util.extensions.CoreExtensions.navigateBack
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
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
                            initializeMap(restaurantDetailsExtra = state.restaurantDetailsExtra)
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

    private fun initializeMap(restaurantDetailsExtra: RestaurantDetailsExtra) {
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.googleMap) as SupportMapFragment

        mapFragment.getMapAsync { googleMap ->
            googleMap.apply {
                val place = LatLng(
                    restaurantDetailsExtra.latLng.first, restaurantDetailsExtra.latLng.second
                )
                binding.tvSchedule.text = restaurantDetailsExtra.schedule

                mapType = GoogleMap.MAP_TYPE_NORMAL
                addMarker(
                    MarkerOptions().position(place).title(restaurantDetailsExtra.name)
                )
                animateCamera(
                    CameraUpdateFactory.newLatLngZoom(
                        place, 15.0f
                    )
                )
            }
        }
    }
}