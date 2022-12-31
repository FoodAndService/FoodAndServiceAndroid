package com.foodandservice.presentation.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.foodandservice.databinding.ItemProductOrderPastBinding
import com.foodandservice.databinding.ItemProductOrderPastExtraBinding
import com.foodandservice.domain.model.ProductOrderPast
import java.util.*

class OrderPastAdapter :
    ListAdapter<ProductOrderPast, OrderPastAdapter.AbstractViewHolder>(OrderPastDiffCallback()) {
    companion object {
        private const val ITEM_PRODUCT_NORMAL = 1
        private const val ITEM_PRODUCT_EXTRA = 2
    }

    abstract class AbstractViewHolder(
        val binding: ViewDataBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        abstract fun bind(item: ProductOrderPast)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbstractViewHolder {
        return if (viewType == ITEM_PRODUCT_NORMAL) ProductOrderPastNormalViewHolder.from(parent)
        else ProductOrderPastExtraViewHolder.from(parent)
    }

    internal class ProductOrderPastNormalViewHolder private constructor(binding: ItemProductOrderPastBinding) :
        AbstractViewHolder(binding) {

        @SuppressLint("SetTextI18n")
        override fun bind(item: ProductOrderPast) {
            val binding = binding as ItemProductOrderPastBinding

            binding.apply {
                tvProductName.text = item.name
                tvPrice.text = item.price
            }
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ProductOrderPastNormalViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemProductOrderPastBinding.inflate(layoutInflater, parent, false)
                return ProductOrderPastNormalViewHolder(binding)
            }
        }
    }

    internal class ProductOrderPastExtraViewHolder private constructor(binding: ItemProductOrderPastExtraBinding) :
        AbstractViewHolder(binding) {

        @SuppressLint("SetTextI18n")
        override fun bind(item: ProductOrderPast) {
            val binding = binding as ItemProductOrderPastExtraBinding

            binding.apply {
                tvProductName.text = item.name
                tvPrice.text = item.price
            }
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ProductOrderPastExtraViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding =
                    ItemProductOrderPastExtraBinding.inflate(layoutInflater, parent, false)
                return ProductOrderPastExtraViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: AbstractViewHolder, position: Int) {
        holder.bind(item = getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return if (this.currentList[position].isExtra) ITEM_PRODUCT_EXTRA else ITEM_PRODUCT_NORMAL
    }
}

class OrderPastDiffCallback : DiffUtil.ItemCallback<ProductOrderPast>() {
    override fun areItemsTheSame(oldItem: ProductOrderPast, newItem: ProductOrderPast) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: ProductOrderPast, newItem: ProductOrderPast) =
        oldItem == newItem
}