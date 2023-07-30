package com.foodandservice.presentation.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
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

                tvProductName.text = productItem.name
                tvPrice.text = "${productItem.price} €"
                tvProductQuantity.text = productItem.quantity.toString()
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

                tvProductName.text = extraItem.name
                tvPrice.text = "${extraItem.price} €"
                tvProductQuantity.text = extraItem.quantity.toString()
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