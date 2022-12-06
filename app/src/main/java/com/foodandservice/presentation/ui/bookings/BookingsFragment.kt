package com.foodandservice.presentation.ui.bookings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.foodandservice.R
import com.foodandservice.databinding.FragmentBookingsBinding
import com.foodandservice.domain.model.Booking
import com.foodandservice.presentation.ui.adapter.BookingAdapter
import java.time.LocalDateTime

class BookingsFragment : Fragment(), BookingAdapter.BookingClickListener {
    private lateinit var binding: FragmentBookingsBinding
    private lateinit var bookingAdapter: BookingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_bookings,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {

        }

        binding.apply {
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun setAdapter() {
        bookingAdapter = BookingAdapter(this)
        binding.rvBookings.adapter = bookingAdapter

        val bookings = listOf(
            Booking(
                restaurantName = "Rosario's Burger",
                diners = 1,
                isActive = true,
                dateTime = LocalDateTime.now()
            ),
            Booking(
                restaurantName = "Foster Hollywood",
                diners = 2,
                isActive = true,
                dateTime = LocalDateTime.now().minusDays(1)
            ),
            Booking(
                restaurantName = "Domino's Pizza",
                diners = 3,
                isActive = false,
                dateTime = LocalDateTime.now().minusDays(3)
            ),
            Booking(
                restaurantName = "Kanival Burger",
                diners = 4,
                isActive = false,
                dateTime = LocalDateTime.now().minusDays(5)
            ),
            Booking(
                restaurantName = "G.O.A.T Burger",
                diners = 5,
                isActive = false,
                dateTime = LocalDateTime.now().minusDays(7)
            )
        )

        bookingAdapter.submitList(bookings)
    }

    override fun onClick(item: Booking) {

    }
}