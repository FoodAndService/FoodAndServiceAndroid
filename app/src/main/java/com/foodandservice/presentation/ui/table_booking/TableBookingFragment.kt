package com.foodandservice.presentation.ui.table_booking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.foodandservice.R
import com.foodandservice.databinding.FragmentTableReservationBinding
import org.koin.android.ext.android.get

class TableBookingFragment : Fragment() {
    private lateinit var binding: FragmentTableReservationBinding
    private val viewModel: TableBookingViewModel = get()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_table_reservation, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.tableBookingState.collect { state ->
                when (state) {
                    is TableBookingState.Success -> {

                    }
                    is TableBookingState.Error -> {
                        TODO("Show error")
                    }
                    is TableBookingState.Idle -> {}
                }
            }
        }

        binding.apply {
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }
}