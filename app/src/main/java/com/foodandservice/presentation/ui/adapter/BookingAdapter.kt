package com.foodandservice.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.foodandservice.R
import com.foodandservice.databinding.ItemBookingBinding
import com.foodandservice.domain.model.Booking
import java.time.format.DateTimeFormatter

class BookingAdapter constructor(private val listener: BookingClickListener) :
    ListAdapter<Booking, BookingAdapter.ViewHolder>(BookingDiffCallBack()) {

    interface BookingClickListener {
        fun onClick(item: Booking)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    class ViewHolder private constructor(private val binding: ItemBookingBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Booking, bookingClickListener: BookingClickListener) {
            binding.apply {
                if (item.diners > 1) tvRestaurantName.text = itemView.context.getString(
                    R.string.item_booking_diners_plural, item.restaurantName, item.diners.toString()
                )
                else tvRestaurantName.text = itemView.context.getString(
                    R.string.item_booking_diners_singular,
                    item.restaurantName,
                    item.diners.toString()
                )
                btnCancel.visibility = if (item.isActive) View.VISIBLE else View.GONE
                tvDateTime.text =
                    item.dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))

                root.setOnClickListener {
                    bookingClickListener.onClick(item)
                }
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemBookingBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class BookingDiffCallBack : DiffUtil.ItemCallback<Booking>() {
    override fun areItemsTheSame(
        oldItem: Booking, newItem: Booking
    ) = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: Booking, newItem: Booking
    ) = oldItem == newItem
}