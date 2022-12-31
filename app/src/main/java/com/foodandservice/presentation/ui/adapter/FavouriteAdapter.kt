package com.foodandservice.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.foodandservice.databinding.ItemFavouriteRestaurantBinding
import com.foodandservice.domain.model.FavouriteRestaurant

class FavouriteAdapter constructor(private val listener: FavouriteRestaurantClickListener) :
    ListAdapter<FavouriteRestaurant, FavouriteAdapter.ViewHolder>(FavouriteRestaurantDiffCallBack()) {

    interface FavouriteRestaurantClickListener {
        fun onClick(item: FavouriteRestaurant)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    class ViewHolder private constructor(private val binding: ItemFavouriteRestaurantBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: FavouriteRestaurant,
            favouriteRestaurantClickListener: FavouriteRestaurantClickListener
        ) {
            binding.apply {
                tvRestaurantName.text = item.name

                root.setOnClickListener {
                    favouriteRestaurantClickListener.onClick(item)
                }
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemFavouriteRestaurantBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class FavouriteRestaurantDiffCallBack : DiffUtil.ItemCallback<FavouriteRestaurant>() {
    override fun areItemsTheSame(
        oldItem: FavouriteRestaurant, newItem: FavouriteRestaurant
    ) = oldItem.name == newItem.name

    override fun areContentsTheSame(
        oldItem: FavouriteRestaurant, newItem: FavouriteRestaurant
    ) = oldItem == newItem
}