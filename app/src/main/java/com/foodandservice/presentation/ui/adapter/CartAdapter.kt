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
import com.foodandservice.domain.model.CartItem
import java.util.*

class CartAdapter constructor(private val clickListener: CartItemClickListener) :
    ListAdapter<CartItem, CartAdapter.AbstractViewHolder>(CartDiffCallback()) {

    interface CartItemClickListener {
        fun onClickSubtractQuantity(cartItem: CartItem, position: Int)
        fun onClickAddQuantity(cartItem: CartItem, position: Int)
    }

    companion object {
        private const val ITEM_CART_NORMAL = 1
        private const val ITEM_CART_EXTRA = 2
    }

    abstract class AbstractViewHolder(
        val binding: ViewDataBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        abstract fun bind(item: CartItem, clickListener: CartItemClickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbstractViewHolder {
        return if (viewType == ITEM_CART_NORMAL) CartItemNormalViewHolder.from(parent)
        else CartItemExtraViewHolder.from(parent)
    }

    internal class CartItemNormalViewHolder private constructor(binding: ItemProductCartBinding) :
        AbstractViewHolder(binding) {

        @SuppressLint("SetTextI18n")
        override fun bind(item: CartItem, clickListener: CartItemClickListener) {
            val binding = binding as ItemProductCartBinding

            binding.apply {
                btnAdd.setOnClickListener {
                    clickListener.onClickAddQuantity(
                        cartItem = item, position = bindingAdapterPosition
                    )
                }

                btnSubtract.setOnClickListener {
                    clickListener.onClickSubtractQuantity(
                        cartItem = item, position = bindingAdapterPosition
                    )
                }

                tvProductName.text = item.name
                tvPrice.text = item.price + "€"
                tvProductQuantity.text = item.quantity.toString()
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
        override fun bind(item: CartItem, clickListener: CartItemClickListener) {
            val binding = binding as ItemProductExtraCartBinding

            binding.apply {
                btnAdd.setOnClickListener {
                    clickListener.onClickAddQuantity(
                        cartItem = item, position = bindingAdapterPosition
                    )
                }

                btnSubtract.setOnClickListener {
                    clickListener.onClickSubtractQuantity(
                        cartItem = item, position = bindingAdapterPosition
                    )
                }

                tvProductName.text = item.name
                tvPrice.text = item.price + "€"
                tvProductQuantity.text = item.quantity.toString()
            }
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): CartItemExtraViewHolder {
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
        return if (this.currentList[position].isExtra) ITEM_CART_EXTRA else ITEM_CART_NORMAL
    }
}

class CartDiffCallback : DiffUtil.ItemCallback<CartItem>() {
    override fun areItemsTheSame(oldItem: CartItem, newItem: CartItem) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: CartItem, newItem: CartItem) = oldItem == newItem
}