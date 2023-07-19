package com.foodandservice.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.foodandservice.R
import com.foodandservice.databinding.ItemProductExtraBinding
import com.foodandservice.domain.model.restaurant_details.RestaurantProductExtra
import com.foodandservice.domain.model.restaurant_details.toUI

class ProductExtraAdapter constructor(
    private val clickListener: ProductExtraClickListener, private val hasStock: Boolean
) : ListAdapter<RestaurantProductExtra, ProductExtraAdapter.ViewHolder>(
    RestaurantProductExtraDiffCallBack()
) {

    interface ProductExtraClickListener {
        fun onClickAddQuantity(productExtra: RestaurantProductExtra, position: Int)
        fun onClickSubtractQuantity(productExtra: RestaurantProductExtra, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(
            item = getItem(position), clickListener = clickListener, hasStock = hasStock
        )
    }

    class ViewHolder private constructor(private val binding: ItemProductExtraBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: RestaurantProductExtra,
            clickListener: ProductExtraClickListener,
            hasStock: Boolean
        ) {
            binding.apply {
                tvProductExtraName.text = item.name
                tvExtraPriceSingle.text =
                    binding.root.context.getString(R.string.product_price_single, item.price.toUI())
                tvExtraPriceTotal.text = item.price.toUI(item.quantity)

                tvProductQuantity.text = item.quantity.toString()

                if (hasStock) {
                    btnAdd.isEnabled = item.quantity < 100
                    btnSubtract.isEnabled = item.quantity > 0
                } else {
                    btnAdd.isEnabled = false
                    btnSubtract.isEnabled = false
                }

                btnAdd.setOnClickListener {
                    clickListener.onClickAddQuantity(
                        productExtra = item, position = bindingAdapterPosition
                    )
                }
                btnSubtract.setOnClickListener {
                    clickListener.onClickSubtractQuantity(
                        productExtra = item, position = bindingAdapterPosition
                    )
                }
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

class RestaurantProductExtraDiffCallBack : DiffUtil.ItemCallback<RestaurantProductExtra>() {
    override fun areItemsTheSame(
        oldItem: RestaurantProductExtra, newItem: RestaurantProductExtra
    ) = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: RestaurantProductExtra, newItem: RestaurantProductExtra
    ) = oldItem == newItem
}