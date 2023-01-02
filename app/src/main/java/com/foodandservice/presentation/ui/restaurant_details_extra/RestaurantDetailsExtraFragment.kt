package com.foodandservice.presentation.ui.restaurant_details_extra

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.foodandservice.R
import com.foodandservice.databinding.FragmentRestaurantDetailsExtraBinding
import com.foodandservice.util.extensions.CoreExtensions.navigateBack
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class RestaurantDetailsExtraFragment : Fragment(), OnMapReadyCallback {
    private lateinit var binding: FragmentRestaurantDetailsExtraBinding
    private lateinit var mapFragment: SupportMapFragment

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

        initGoogleMap()

        val schedule =
            "Lunes: Cerrado\n" + "Martes: 12:00 a 17:00 y 20:30 a 02:00\n" + "Miércoles: 12:00 a 17:00 y 20:30 a 02:00\n" + "Jueves: 12:00 a 17:00 y 20:30 a 02:00\n" + "Viernes: 12:00 a 17:00 y 20:30 a 02:00\n" + "Sábado: 12:00 a 17:00 y 20:30 a 02:00\n" + "Domingo: 12:00 a 17:00 y 20:30 a 02:00"

        binding.apply {
            tvSchedule.text = schedule

            btnBack.setOnClickListener {
                navigateBack()
            }
        }
    }

    private fun initGoogleMap() {
        mapFragment = childFragmentManager.findFragmentById(R.id.googleMap) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val place = LatLng(36.71429686692496, -4.433230427633246)

        googleMap.apply {
            mapType = GoogleMap.MAP_TYPE_NORMAL

            addMarker(
                MarkerOptions().position(place).title("Domino's Pizza")
            )

            animateCamera(CameraUpdateFactory.newLatLngZoom(place, 15.0f))
        }
    }
}