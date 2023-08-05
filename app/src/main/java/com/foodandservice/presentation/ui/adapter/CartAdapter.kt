package com.foodandservice.presentation.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.foodandservice.R
import com.foodandservice.databinding.ItemProductCartBinding
import com.foodandservice.databinding.ItemProductExtraCartBinding
import com.foodandservice.domain.model.cart.RestaurantCartItem
import java.util.*

class CartAdapter constructor(private val clickListener: CartItemClickListener) :
    ListAdapter<RestaurantCartItem, CartAdapter.AbstractViewHolder>(RestaurantCartItemDiffCallback()) {

    interface CartItemClickListener {
        fun onClickSubtractQuantity(restaurantCartItem: RestaurantCartItem, position: Int)
        fun onClickAddQuantity(restaurantCartItem: RestaurantCartItem, position: Int)
    }

    companion object {
        private const val CART_ITEM_PRODUCT = 1
        private const val CART_ITEM_PRODUCT_EXTRA = 2
    }

    abstract class AbstractViewHolder(
        val binding: ViewDataBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        abstract fun bind(item: RestaurantCartItem, clickListener: CartItemClickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbstractViewHolder {
        return if (viewType == CART_ITEM_PRODUCT) CartItemNormalViewHolder.from(parent)
        else CartItemExtraViewHolder.from(parent)
    }

    internal class CartItemNormalViewHolder private constructor(binding: ItemProductCartBinding) :
        AbstractViewHolder(binding) {

        @SuppressLint("SetTextI18n")
        override fun bind(item: RestaurantCartItem, clickListener: CartItemClickListener) {
            val binding = binding as ItemProductCartBinding
            val productItem = (item as? RestaurantCartItem.Product)?.item ?: return

            binding.apply {
                tvProductName.text = productItem.name
                tvPriceOld.apply {
                    visibility = if (productItem.hasDiscount) View.VISIBLE else View.GONE
                    text = productItem.priceUI
                }
                tvPrice.text = binding.root.context.getString(R.string.product_price_single,
                    productItem.discountedPriceUI.takeIf { productItem.hasDiscount }
                        ?: productItem.priceUI)
                tvPriceTotal.text = productItem.priceTotalUI
                tvProductQuantity.text = productItem.quantity.toString()

                tvProductNote.apply {
                    visibility =
                        if (productItem.productNote.isNotEmpty()) View.VISIBLE else View.GONE
                    text = "*${productItem.productNote}"
                }

                Glide.with(itemView).load(productItem.productImage).centerCrop().into(ivProduct)

                btnAdd.setOnClickListener {
                    clickListener.onClickAddQuantity(
                        restaurantCartItem = item, position = bindingAdapterPosition
                    )
                }

                btnSubtract.setOnClickListener {
                    clickListener.onClickSubtractQuantity(
                        restaurantCartItem = item, position = bindingAdapterPosition
                    )
                }
            }
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): CartItemNormalViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemProductCartBinding.inflate(layoutInflater, parent, false)
                return CartItemNormalViewHolder(binding)
            }
        }
    }

    internal class CartItemExtraViewHolder private constructor(binding: ItemProductExtraCartBinding) :
        AbstractViewHolder(binding) {

        @SuppressLint("SetTextI18n")
        override fun bind(item: RestaurantCartItem, clickListener: CartItemClickListener) {
            val binding = binding as ItemProductExtraCartBinding
            val extraItem = (item as? RestaurantCartItem.ProductExtra)?.extra ?: return

            binding.apply {
                tvProductName.text = extraItem.name
                tvPrice.text =
                    binding.root.context.getString(R.string.product_price_single, extraItem.priceUI)
                tvProductQuantity.text = extraItem.quantity.toString()

                btnAdd.setOnClickListener {
                    clickListener.onClickAddQuantity(
                        restaurantCartItem = item, position = bindingAdapterPosition
                    )
                }

                btnSubtract.setOnClickListener {
                    clickListener.onClickSubtractQuantity(
                        restaurantCartItem = item, position = bindingAdapterPosition
                    )
                }
            }
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): AbstractViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemProductExtraCartBinding.inflate(layoutInflater, parent, false)
                return CartItemExtraViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: AbstractViewHolder, position: Int) {
        holder.bind(item = getItem(position), clickListener = clickListener)
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is RestaurantCartItem.Product -> CART_ITEM_PRODUCT
            is RestaurantCartItem.ProductExtra -> CART_ITEM_PRODUCT_EXTRA
        }
    }
}

class RestaurantCartItemDiffCallback : DiffUtil.ItemCallback<RestaurantCartItem>() {
    override fun areItemsTheSame(
        oldItem: RestaurantCartItem, newItem: RestaurantCartItem
    ): Boolean {
        return when {
            oldItem is RestaurantCartItem.Product && newItem is RestaurantCartItem.Product -> oldItem.item.productId == newItem.item.productId

            oldItem is RestaurantCartItem.ProductExtra && newItem is RestaurantCartItem.ProductExtra -> oldItem.extra.extraId == newItem.extra.extraId

            else -> false
        }
    }

    override fun areContentsTheSame(
        oldItem: RestaurantCartItem, newItem: RestaurantCartItem
    ): Boolean {
        return oldItem == newItem
    }
}