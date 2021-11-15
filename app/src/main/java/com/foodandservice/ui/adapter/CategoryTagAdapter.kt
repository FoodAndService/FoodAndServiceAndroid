package com.foodandservice.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.foodandservice.data.model.CategoryTag
import com.foodandservice.databinding.ItemCategoryTagBinding

class CategoryTagAdapter constructor(private val listener: CategoryTagClickListener) :
    ListAdapter<CategoryTag, CategoryTagAdapter.ViewHolder>(CategoryTagDiffCallback()) {

    interface CategoryTagClickListener {
        fun onClick(categoryTag: CategoryTag)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    class ViewHolder private constructor(private val binding: ItemCategoryTagBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CategoryTag, listener: CategoryTagClickListener) {
            binding.chipCategoryTagText.text = item.name

            binding.root.setOnClickListener {
                listener.onClick(item)
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

class CategoryTagDiffCallback : DiffUtil.ItemCallback<CategoryTag>() {
    override fun areItemsTheSame(oldItem: CategoryTag, newItem: CategoryTag): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CategoryTag, newItem: CategoryTag): Boolean {
        return oldItem == newItem
    }
}