package com.foodandservice.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.foodandservice.databinding.ItemRestaurantBinding
import com.foodandservice.domain.model.Restaurant

class RestaurantAdapter constructor(private val listener: RestaurantClickListener) :
    PagingDataAdapter<Restaurant, RestaurantAdapter.ViewHolder>(RestaurantDiffCallBack()) {

    interface RestaurantClickListener {
        fun onClick(item: Restaurant)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it, listener) }
    }

    class ViewHolder private constructor(private val binding: ItemRestaurantBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Restaurant, restaurantClickListener: RestaurantClickListener) {
            binding.apply {
                tvRestaurantName.text = item.name
                Glide.with(itemView).load(item.logo).centerCrop().into(restLogo)
                Glide.with(itemView).load(item.banner).into(restBackground)

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
    override fun areItemsTheSame(oldItem: Restaurant, newItem: Restaurant) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Restaurant, newItem: Restaurant) = oldItem == newItem
}