package com.foodandservice.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.foodandservice.databinding.ItemProductExtraBinding
import com.foodandservice.domain.model.ProductExtra

class ProductExtraAdapter constructor(private val clickListener: ProductExtraClickListener) :
    ListAdapter<ProductExtra, ProductExtraAdapter.ViewHolder>(ProductExtraDiffCallBack()) {

    interface ProductExtraClickListener {
        fun onClickSubtractQuantity(productExtra: ProductExtra, position: Int)
        fun onClickAddQuantity(productExtra: ProductExtra, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(
            item = getItem(position),
            clickListener = clickListener,
            isLast = position == itemCount - 1
        )
    }

    class ViewHolder private constructor(private val binding: ItemProductExtraBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ProductExtra, clickListener: ProductExtraClickListener, isLast: Boolean) {
            binding.apply {
                tvProductExtraName.text = item.name
                tvProductExtraPrice.text = item.price + "€"
                tvProductQuantity.text = item.quantity.toString()

                btnAdd.setOnClickListener {
                    clickListener.onClickAddQuantity(
                        productExtra = item,
                        position = bindingAdapterPosition
                    )
                }
                btnSubtract.setOnClickListener {
                    clickListener.onClickSubtractQuantity(
                        productExtra = item, position = bindingAdapterPosition
                    )
                }

                divider.visibility = if (isLast) View.GONE else View.VISIBLE
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemProductExtraBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class ProductExtraDiffCallBack : DiffUtil.ItemCallback<ProductExtra>() {
    override fun areItemsTheSame(
        oldItem: ProductExtra, newItem: ProductExtra
    ) = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: ProductExtra, newItem: ProductExtra
    ) = oldItem == newItem
}