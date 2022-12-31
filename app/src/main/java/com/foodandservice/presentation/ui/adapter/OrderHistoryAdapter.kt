package com.foodandservice.presentation.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.foodandservice.R
import com.foodandservice.databinding.ItemOrderHistoryBinding
import com.foodandservice.domain.model.Order
import java.time.ZoneId
import java.util.*
import java.util.concurrent.TimeUnit

class OrderHistoryAdapter constructor(private val orderClickListener: OrderClickListener) :
    ListAdapter<Order, OrderHistoryAdapter.ViewHolder>(OrderHistoryDiffCallBack()) {

    interface OrderClickListener {
        fun onClick(item: Order)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(item = getItem(position), orderClickListener = orderClickListener)
    }

    class ViewHolder private constructor(private val binding: ItemOrderHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(item: Order, orderClickListener: OrderClickListener) {
            val diffInMillis =
                Calendar.getInstance().timeInMillis - item.date.atZone(ZoneId.systemDefault())
                    .toInstant().toEpochMilli()
            val diffInDays = TimeUnit.MILLISECONDS.toDays(diffInMillis)

            binding.apply {
                tvRestaurantName.text = item.restaurantName
                tvPrice.text = "${item.amount}€"
                tvDate.text = itemView.context.getString(R.string.tv_days_ago, diffInDays)
                cvOrder.setOnClickListener {
                    orderClickListener.onClick(item = item)
                }
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

class OrderHistoryDiffCallBack : DiffUtil.ItemCallback<Order>() {
    override fun areItemsTheSame(
        oldItem: Order, newItem: Order
    ) = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: Order, newItem: Order
    ) = oldItem == newItem
}