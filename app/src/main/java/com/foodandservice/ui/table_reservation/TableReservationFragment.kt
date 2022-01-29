package com.foodandservice.ui.table_reservation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.foodandservice.R
import com.foodandservice.databinding.FragmentTableReservationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TableReservationFragment : Fragment() {
    private lateinit var binding: FragmentTableReservationBinding
    private val viewModel by viewModels<TableReservationViewModelImpl>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_table_reservation, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getState().observe(viewLifecycleOwner, {
            when (it) {
                TableReservationViewModel.State.DinersEmptyError -> {

                }
                TableReservationViewModel.State.DateEmptyError -> {

                }
                TableReservationViewModel.State.HourEmptyError -> {

                }
                TableReservationViewModel.State.Success -> {

                }
            }
        })
    }
}