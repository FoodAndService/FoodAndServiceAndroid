package com.foodandservice.presentation.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.foodandservice.databinding.ItemProductBinding
import com.foodandservice.databinding.ItemProductRefillBinding
import com.foodandservice.domain.model.Product

class ProductAdapter constructor(private val listener: ProductClickListener) :
    ListAdapter<Product, ProductAdapter.AbstractViewHolder>(ProductDiffCallback()) {

    companion object {
        private const val ITEM_PRODUCT_NORMAL = 1
        private const val ITEM_PRODUCT_REFILL = 2
    }

    interface ProductClickListener {
        fun onClick(product: Product)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbstractViewHolder {
        return when (viewType) {
            ITEM_PRODUCT_NORMAL -> ProductNormalViewHolder.from(parent)
            ITEM_PRODUCT_REFILL -> ProductRefillViewHolder.from(parent)
            else -> {
                throw Exception("ViewHolder not found")
            }
        }
    }

    abstract class AbstractViewHolder(
        val binding: ViewDataBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        abstract fun bind(item: Product, clickListener: ProductClickListener)
    }

    internal class ProductNormalViewHolder private constructor(binding: ItemProductBinding) :
        AbstractViewHolder(binding) {

        @SuppressLint("SetTextI18n")
        override fun bind(item: Product, clickListener: ProductClickListener) {
            val binding = binding as ItemProductBinding
            binding.apply {
                tvProductName.text = item.name
                tvPrice.text = item.price + "€"

                root.setOnClickListener {
                    clickListener.onClick(item)
                }
            }
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ProductNormalViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemProductBinding.inflate(layoutInflater, parent, false)
                return ProductNormalViewHolder(binding)
            }
        }
    }

    internal class ProductRefillViewHolder private constructor(binding: ItemProductRefillBinding) :
        AbstractViewHolder(binding) {

        @SuppressLint("SetTextI18n")
        override fun bind(item: Product, clickListener: ProductClickListener) {
            val binding = binding as ItemProductRefillBinding
            binding.apply {
                tvProductName.text = item.name
                tvPrice.text = item.price + "€"

                root.setOnClickListener {
                    clickListener.onClick(item)
                }
            }
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ProductRefillViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemProductRefillBinding.inflate(layoutInflater, parent, false)
                return ProductRefillViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: AbstractViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    override fun getItemViewType(position: Int): Int {
        return if (this.currentList[position].refill) ITEM_PRODUCT_REFILL else ITEM_PRODUCT_NORMAL
    }
}

class ProductDiffCallback : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }
}