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

class TableReservationFragment : Fragment() {
    private lateinit var databinding: FragmentTableReservationBinding
    private val viewModel: TableReservationViewModelImpl by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        databinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_table_reservation, container, false)
        return databinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getState().observe(viewLifecycleOwner, {
            when(it){
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