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
import java.time.LocalDateTime

class BookingsFragment : Fragment(), BookingAdapter.BookingClickListener {
    private lateinit var binding: FragmentBookingsBinding
    private lateinit var bookingAdapter: BookingAdapter

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

            }
        }

        binding.apply {
            btnBack.setOnClickListener {
                navigateBack()
            }
        }
    }

    private fun setAdapter() {
        val bookings = listOf(
            Booking(
                id = "1",
                restaurantName = "Rosario's Burger",
                diners = 1,
                isActive = true,
                dateTime = LocalDateTime.now()
            ), Booking(
                id = "2",
                restaurantName = "Foster Hollywood",
                diners = 2,
                isActive = true,
                dateTime = LocalDateTime.now().minusDays(1)
            ), Booking(
                id = "3",
                restaurantName = "Domino's Pizza",
                diners = 3,
                isActive = false,
                dateTime = LocalDateTime.now().minusDays(3)
            ), Booking(
                id = "4",
                restaurantName = "Kanival Burger",
                diners = 4,
                isActive = false,
                dateTime = LocalDateTime.now().minusDays(5)
            ), Booking(
                id = "5",
                restaurantName = "G.O.A.T Burger",
                diners = 5,
                isActive = false,
                dateTime = LocalDateTime.now().minusDays(7)
            )
        )

        bookingAdapter = BookingAdapter(this).also { adapter ->
            binding.rvBookings.adapter = adapter
            adapter.submitList(bookings)
        }
    }

    override fun onClick(item: Booking) {

    }
}