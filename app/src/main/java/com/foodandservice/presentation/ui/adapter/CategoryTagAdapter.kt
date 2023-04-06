package com.foodandservice.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.foodandservice.databinding.ItemCategoryTagBinding
import com.foodandservice.domain.model.RestaurantCategory

class CategoryTagAdapter constructor(private val listener: CategoryTagClickListener) :
    ListAdapter<RestaurantCategory, CategoryTagAdapter.ViewHolder>(CategoryTagDiffCallback()) {

    interface CategoryTagClickListener {
        fun onClick(restaurantCategory: RestaurantCategory)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    class ViewHolder private constructor(private val binding: ItemCategoryTagBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RestaurantCategory, categoryTagClickListener: CategoryTagClickListener) {
            binding.apply {
                chipCategoryTagText.text = item.name
                root.setOnClickListener { categoryTagClickListener.onClick(item) }
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemCategoryTagBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class CategoryTagDiffCallback : DiffUtil.ItemCallback<RestaurantCategory>() {
    override fun areItemsTheSame(oldItem: RestaurantCategory, newItem: RestaurantCategory) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: RestaurantCategory,
        newItem: RestaurantCategory
    ) = oldItem == newItem
}