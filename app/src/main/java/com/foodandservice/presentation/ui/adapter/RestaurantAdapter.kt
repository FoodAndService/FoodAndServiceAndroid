package com.foodandservice.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.foodandservice.databinding.ItemRestaurantBinding
import com.foodandservice.domain.model.Restaurant

class RestaurantAdapter constructor(private val listener: RestaurantClickListener) :
    ListAdapter<Restaurant, RestaurantAdapter.ViewHolder>(RestaurantDiffCallBack()) {

    interface RestaurantClickListener {
        fun onClick(item: Restaurant)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    class ViewHolder private constructor(private val binding: ItemRestaurantBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Restaurant, restaurantClickListener: RestaurantClickListener) {
            binding.apply {
                tvRestaurantName.text = item.name
                ratingRestaurant.rating = item.rating
                tvDistance.text = "A ${item.distance.toDouble() / 1000} km"

                root.setOnClickListener {
                    restaurantClickListener.onClick(item)
                }
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemRestaurantBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class RestaurantDiffCallBack : DiffUtil.ItemCallback<Restaurant>() {
    override fun areItemsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
        return oldItem == newItem
    }
}