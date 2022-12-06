package com.foodandservice.presentation.ui.table_reservation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.foodandservice.R
import com.foodandservice.databinding.FragmentTableReservationBinding
import org.koin.android.ext.android.get

class TableReservationFragment : Fragment() {
    private lateinit var binding: FragmentTableReservationBinding
    private val viewModel: TableReservationViewModel = get()

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
            viewModel.tableReservationState.collect { state ->
                when (state) {
                    is TableReservationState.Success -> {

                    }
                    is TableReservationState.Error -> {
                        TODO("Show error")
                    }
                    is TableReservationState.Idle -> {}
                }
            }
        }
    }
}