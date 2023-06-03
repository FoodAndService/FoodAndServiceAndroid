package com.foodandservice.presentation.ui.restaurant_details_extra

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.foodandservice.R
import com.foodandservice.databinding.FragmentRestaurantDetailsExtraBinding
import com.foodandservice.util.IntentUtils.Companion.openMapsWithLocation
import com.foodandservice.util.IntentUtils.Companion.openPhoneWithNumber
import com.foodandservice.util.extensions.CoreExtensions.navigateBack
import com.foodandservice.util.extensions.CoreExtensions.showToast
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.format.TextStyle
import java.util.Locale

class RestaurantDetailsExtraFragment : Fragment() {
    private lateinit var binding: FragmentRestaurantDetailsExtraBinding
    private val restaurantDetails by lazy {
        RestaurantDetailsExtraFragmentArgs.fromBundle(requireArguments()).restaurantDetails
    }

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

        setExtraDetails()

        binding.apply {
            btnBack.setOnClickListener {
                navigateBack()
            }

            btnRestaurantAddress.setOnClickListener {
                try {
                    openMapsWithLocation(
                        latitude = restaurantDetails.address.latitude,
                        longitude = restaurantDetails.address.longitude,
                        context = requireContext()
                    )
                } catch (_: Exception) {
                    showToast(getString(R.string.error_no_google_maps_found))
                }
            }

            btnRestaurantPhone.setOnClickListener {
                try {
                    openPhoneWithNumber(phone = restaurantDetails.phone, context = requireContext())
                } catch (_: Exception) {
                    showToast(getString(R.string.error_dialer))
                }
            }
        }
    }

    private fun setExtraDetails() {
        binding.apply {
            tvRestaurantName.text = restaurantDetails.name
            tvCategoryName.text = restaurantDetails.categoryName
            btnRestaurantAddress.text = buildAddress()
            btnRestaurantPhone.text = formatPhone(restaurantDetails.phone)
            tvSchedule.text = buildSchedule()
        }
    }

    private fun buildSchedule(): CharSequence {
        val today = LocalDateTime.now().dayOfWeek.value
        val schedule = restaurantDetails.schedule
        val scheduleDays = SpannableStringBuilder()

        for (dayOfWeek in DayOfWeek.values()) {
            val matchingSchedules = schedule.filter { it.weekDay == dayOfWeek.value }
            val dayOfWeekString = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault())
                .replaceFirstChar(Char::uppercase)
            val isToday = dayOfWeek.value == today

            val scheduleDay: SpannableString = if (matchingSchedules.isNotEmpty()) {
                val scheduleString = matchingSchedules.joinToString(", ") {
                    if (it.startHour == 0 && it.startMinutes == 0 && it.endHour == 23 && it.endMinutes == 59) {
                        getString(R.string.schedule_open_24h)
                    } else if (it.endHour == 23 && it.endMinutes == 59) {
                        val start = String.format("%02d:%02d", it.startHour, it.startMinutes)
                        "$start - 24"
                    } else {
                        val start = String.format("%02d:%02d", it.startHour, it.startMinutes)
                        val end = String.format("%02d:%02d", it.endHour, it.endMinutes)
                        "$start - $end"
                    }
                }
                SpannableString("${dayOfWeekString}: $scheduleString")
            } else {
                SpannableString(
                    "${dayOfWeekString}: ${
                        getString(
                            R.string.restaurant_opening_status_closed
                        )
                    }"
                )
            }

            if (isToday) {
                scheduleDay.setSpan(
                    StyleSpan(Typeface.BOLD),
                    0,
                    scheduleDay.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }

            scheduleDays.appendLine(scheduleDay)
        }

        return scheduleDays
    }

    private fun buildAddress(): String {
        val address = restaurantDetails.address
        return "${address.name} ${address.number}, ${address.postalCode}, ${address.city}, ${address.country}"
    }

    private fun formatPhone(phoneNumber: String): String {
        val countryCode = phoneNumber.substring(0, phoneNumber.length - 9)
        val formattedNumber = StringBuilder(phoneNumber.substring(countryCode.length))
        val insertPositions = intArrayOf(3, 6, 9)

        for (position in insertPositions) {
            if (formattedNumber.length > position) {
                formattedNumber.insert(position, " ")
            }
        }
        return "$countryCode $formattedNumber"
    }
}