package com.foodandservice.presentation.ui.bookings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.foodandservice.databinding.FragmentBookingsBinding
import com.foodandservice.domain.model.Booking
import com.foodandservice.presentation.ui.adapter.BookingAdapter
import com.foodandservice.util.extensions.CoreExtensions.navigateBack
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get

class BookingsFragment : Fragment(), BookingAdapter.BookingClickListener {
    private lateinit var binding: FragmentBookingsBinding
    private lateinit var bookingAdapter: BookingAdapter
    private val viewModel: BookingsViewModel = get()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.bookingsState.collect { state ->
                    when (state) {
                        is BookingsState.Success -> {
                            bookingAdapter.submitList(state.bookings)
                        }
                        is BookingsState.Loading -> {
                            setLoadingState()
                        }
                        is BookingsState.Error -> {

                        }
                        is BookingsState.Idle -> {
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
        }
    }

    private fun setLoadingState() {

    }

    private fun setIdleState() {

    }

    private fun setAdapter() {
        bookingAdapter = BookingAdapter(this).also { adapter ->
            binding.rvBookings.adapter = adapter
        }
    }

    override fun onClick(item: Booking) {

    }
}