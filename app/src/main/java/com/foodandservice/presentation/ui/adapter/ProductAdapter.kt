package com.foodandservice.presentation.ui.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.foodandservice.R
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

    abstract class AbstractViewHolder(
        val binding: ViewDataBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        abstract fun bind(item: Product, clickListener: ProductClickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbstractViewHolder {
        return if (viewType == ITEM_PRODUCT_NORMAL) ProductNormalViewHolder.from(parent)
        else ProductRefillViewHolder.from(parent)
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

                setStockStyle(product = this, inStock = item.inStock)
            }
            binding.executePendingBindings()
        }

        private fun setStockStyle(product: ItemProductBinding, inStock: Boolean) {
            when (inStock) {
                false -> {
                    product.apply {
                        mainCardView.apply {
                            setCardBackgroundColor(
                                ContextCompat.getColor(
                                    this@ProductNormalViewHolder.binding.root.context,
                                    R.color.background_not_in_stock
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
        override fun bind(item: Product, clickListener: ProductClickListener) {
            val binding = binding as ItemProductRefillBinding
            binding.apply {
                tvProductName.text = item.name
                tvPrice.text = item.price + "€"
                root.setOnClickListener {
                    clickListener.onClick(item)
                }

                setStockStyle(product = this, inStock = item.inStock)
            }
            binding.executePendingBindings()
        }

        private fun setStockStyle(product: ItemProductRefillBinding, inStock: Boolean) {
            when (inStock) {
                false -> {
                    product.apply {
                        mainCardView.apply {
                            setCardBackgroundColor(
                                ContextCompat.getColor(
                                    this@ProductRefillViewHolder.binding.root.context,
                                    R.color.background_not_in_stock
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
        return if (this.currentList[position].isRefill) ITEM_PRODUCT_REFILL else ITEM_PRODUCT_NORMAL
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