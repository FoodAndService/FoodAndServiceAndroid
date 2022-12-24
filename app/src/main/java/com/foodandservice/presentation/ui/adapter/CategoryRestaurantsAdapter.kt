package com.foodandservice.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.foodandservice.databinding.ItemCategoryBinding
import com.foodandservice.domain.model.CategoryRestaurants
import com.foodandservice.presentation.ui.adapter.CategoryRestaurantsAdapter.ViewHolder
import com.foodandservice.util.RecyclerViewItemDecoration

class CategoryRestaurantsAdapter constructor(private val listener: RestaurantAdapter.RestaurantClickListener) :
    ListAdapter<CategoryRestaurants, ViewHolder>(CategoryRestaurantsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    class ViewHolder private constructor(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: CategoryRestaurants,
            listener: RestaurantAdapter.RestaurantClickListener
        ) {
            binding.tvCategory.text = item.name
            val adapter = RestaurantAdapter(listener)
            adapter.submitList(item.restaurants)
            binding.rvRestaurant.adapter = adapter
            binding.rvRestaurant.addItemDecoration(RecyclerViewItemDecoration(32))
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemCategoryBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class CategoryRestaurantsDiffCallback : DiffUtil.ItemCallback<CategoryRestaurants>() {
    override fun areItemsTheSame(
        oldItem: CategoryRestaurants,
        newItem: CategoryRestaurants
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: CategoryRestaurants,
        newItem: CategoryRestaurants
    ): Boolean {
        return oldItem == newItem
    }
}