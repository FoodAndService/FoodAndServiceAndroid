package com.foodandservice.presentation.ui.restaurant_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.foodandservice.R
import com.foodandservice.databinding.FragmentRestaurantInfoBinding
import com.foodandservice.util.ScheduleArrayAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantInfoFragment : Fragment() {
    private lateinit var binding: FragmentRestaurantInfoBinding
    private val viewModel by viewModels<RestaurantInfoViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_restaurant_info, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val schedule = listOf(
            "Lunes: Cerrado\n" +
            "Martes: 12:00 a 17:00 y 20:30 a 02:00\n" +
            "Miércoles: 12:00 a 17:00 y 20:30 a 02:00\n" +
            "Jueves: 12:00 a 17:00 y 20:30 a 02:00\n" +
            "Viernes: 12:00 a 17:00 y 20:30 a 02:00\n" +
            "Sábado: 12:00 a 17:00 y 20:30 a 02:00\n" +
            "Domingo: 12:00 a 17:00 y 20:30 a 02:00"
        )
        val adapter = ScheduleArrayAdapter(requireContext(), R.layout.item_schedule, schedule)
        binding.scheduleMenu.setAdapter(adapter)
    }
}