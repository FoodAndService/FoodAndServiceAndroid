package com.foodandservice.presentation.ui.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.foodandservice.R
import com.foodandservice.databinding.ItemProductBinding
import com.foodandservice.databinding.ItemProductRefillBinding
import com.foodandservice.domain.model.restaurant_details.RestaurantProduct
import com.foodandservice.domain.model.restaurant_details.toUI

class RestaurantProductAdapter constructor(private val listener: RestaurantProductClickListener) :
    ListAdapter<RestaurantProduct, RestaurantProductAdapter.AbstractViewHolder>(
        RestaurantProductDiffCallback()
    ) {

    companion object {
        private const val ITEM_PRODUCT_NORMAL = 1
        private const val ITEM_PRODUCT_REFILL = 2
    }

    interface RestaurantProductClickListener {
        fun onClick(productId: String)
    }

    abstract class AbstractViewHolder(
        val binding: ViewDataBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        abstract fun bind(item: RestaurantProduct, clickListener: RestaurantProductClickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbstractViewHolder {
        return if (viewType == ITEM_PRODUCT_NORMAL) ProductNormalViewHolder.from(parent)
        else ProductRefillViewHolder.from(parent)
    }

    internal class ProductNormalViewHolder private constructor(binding: ItemProductBinding) :
        AbstractViewHolder(binding) {

        @SuppressLint("SetTextI18n")
        override fun bind(item: RestaurantProduct, clickListener: RestaurantProductClickListener) {
            val binding = binding as ItemProductBinding
            binding.apply {
                tvProductName.text = item.name
                if (item.discountedPrice.value > 0) {
                    tvPrice.text = item.discountedPrice.toUI()
                    tvPriceOld.text = item.price.toUI()
                    tvPriceOld.visibility = View.VISIBLE
                } else {
                    tvPrice.text = item.price.toUI()
                }
                mainCardView.setOnClickListener {
                    clickListener.onClick(item.id)
                }
                setStockStyle(product = this, hasStock = item.hasStock)
                Glide.with(itemView).load(item.image).centerCrop().into(ivProduct)
            }
            binding.executePendingBindings()
        }

        private fun setStockStyle(product: ItemProductBinding, hasStock: Boolean) {
            when (hasStock) {
                false -> {
                    product.apply {
                        mainCardView.apply {
                            setCardBackgroundColor(
                                ContextCompat.getColor(
                                    this@ProductNormalViewHolder.binding.root.context,
                                    R.color.card_background_not_in_stock
                                )
                            )
                        }

                        ivProduct.setColorFilter(Color.DKGRAY, PorterDuff.Mode.MULTIPLY)

                        tvPrice.setTextColor(
                            ContextCompat.getColor(
                                this.root.context, R.color.product_not_in_stock
                            )
                        )

                        tvProductName.apply {
                            setTextColor(
                                ContextCompat.getColor(
                                    this@ProductNormalViewHolder.binding.root.context,
                                    R.color.product_not_in_stock
                                )
                            )

                            paintFlags = tvProductName.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                        }
                    }
                }

                true -> {
                    product.apply {
                        mainCardView.apply {
                            setCardBackgroundColor(Color.WHITE)
                        }

                        ivProduct.clearColorFilter()

                        tvPrice.setTextColor(
                            ContextCompat.getColor(
                                this.root.context, R.color.fys_primary
                            )
                        )

                        tvProductName.apply {
                            setTextColor(
                                ContextCompat.getColor(
                                    this@ProductNormalViewHolder.binding.root.context,
                                    R.color.text_normal
                                )
                            )

                            paintFlags = 0
                        }
                    }
                }
            }
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
        override fun bind(item: RestaurantProduct, clickListener: RestaurantProductClickListener) {
            val binding = binding as ItemProductRefillBinding
            binding.apply {
                tvProductName.text = item.name
                tvPrice.text = item.price.toUI()
                mainCardView.setOnClickListener {
                    clickListener.onClick(item.id)
                }

                setStockStyle(product = this, hasStock = item.hasStock)
            }
            binding.executePendingBindings()
        }

        private fun setStockStyle(product: ItemProductRefillBinding, hasStock: Boolean) {
            when (hasStock) {
                false -> {
                    product.apply {
                        mainCardView.apply {
                            setCardBackgroundColor(
                                ContextCompat.getColor(
                                    this@ProductRefillViewHolder.binding.root.context,
                                    R.color.card_background_not_in_stock
                                )
                            )
                        }

                        cardRefill.setCardBackgroundColor(
                            ContextCompat.getColor(
                                this.root.context, R.color.text_dark
                            )
                        )

                        ivProduct.setColorFilter(Color.DKGRAY, PorterDuff.Mode.MULTIPLY)

                        tvPrice.setTextColor(
                            ContextCompat.getColor(
                                this.root.context, R.color.product_not_in_stock
                            )
                        )

                        tvProductName.apply {
                            setTextColor(
                                ContextCompat.getColor(
                                    this@ProductRefillViewHolder.binding.root.context,
                                    R.color.product_not_in_stock
                                )
                            )

                            paintFlags = tvProductName.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                        }
                    }
                }

                true -> {
                    product.apply {
                        mainCardView.apply {
                            setCardBackgroundColor(Color.WHITE)
                        }

                        cardRefill.setCardBackgroundColor(
                            ContextCompat.getColor(
                                this.root.context, R.color.fys_primary
                            )
                        )

                        ivProduct.clearColorFilter()

                        tvPrice.setTextColor(
                            ContextCompat.getColor(
                                this.root.context, R.color.fys_primary
                            )
                        )

                        tvProductName.apply {
                            setTextColor(
                                ContextCompat.getColor(
                                    this@ProductRefillViewHolder.binding.root.context,
                                    R.color.text_normal
                                )
                            )

                            paintFlags = 0
                        }
                    }
                }
            }
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
        // Change this when backend sends product refill possibility
        // return if (this.currentList[position].isRefill) ITEM_PRODUCT_REFILL else ITEM_PRODUCT_NORMAL
        return ITEM_PRODUCT_NORMAL
    }
}

class RestaurantProductDiffCallback : DiffUtil.ItemCallback<RestaurantProduct>() {
    override fun areItemsTheSame(oldItem: RestaurantProduct, newItem: RestaurantProduct) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: RestaurantProduct, newItem: RestaurantProduct) =
        oldItem == newItem
}