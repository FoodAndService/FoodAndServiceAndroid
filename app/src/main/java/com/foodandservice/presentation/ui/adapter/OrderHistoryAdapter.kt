package com.foodandservice.presentation.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.foodandservice.R
import com.foodandservice.databinding.ItemOrderHistoryBinding
import com.foodandservice.domain.model.OrderHistory
import java.time.ZoneId
import java.util.*
import java.util.concurrent.TimeUnit

class OrderHistoryAdapter constructor(private val listener: OrderHistoryClickListener) :
    ListAdapter<OrderHistory, OrderHistoryAdapter.ViewHolder>(OrderHistoryDiffCallBack()) {

    interface OrderHistoryClickListener {
        fun onClick(item: OrderHistory)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    class ViewHolder private constructor(private val binding: ItemOrderHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(item: OrderHistory, listener: OrderHistoryClickListener) {
            val diffInMillis =
                Calendar.getInstance().timeInMillis - item.date.atZone(ZoneId.systemDefault())
                    .toInstant().toEpochMilli()
            val diffInDays = TimeUnit.MILLISECONDS.toDays(diffInMillis)

            binding.tvRestaurantName.text = item.restaurantName
            binding.tvPrice.text = "${item.amount}€"
            binding.tvDate.text =
                itemView.context.getString(R.string.tv_order_history_days_ago, diffInDays)

            binding.root.setOnClickListener {
                listener.onClick(item)
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemOrderHistoryBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class OrderHistoryDiffCallBack : DiffUtil.ItemCallback<OrderHistory>() {
    override fun areItemsTheSame(
        oldItem: OrderHistory,
        newItem: OrderHistory
    ): Boolean {
        return oldItem.date == newItem.date
    }

    override fun areContentsTheSame(
        oldItem: OrderHistory,
        newItem: OrderHistory
    ): Boolean {
        return oldItem == newItem
    }
}