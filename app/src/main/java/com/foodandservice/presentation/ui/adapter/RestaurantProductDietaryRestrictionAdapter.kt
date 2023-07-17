package com.foodandservice.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.foodandservice.R
import com.foodandservice.databinding.ProductDietaryRestrictionHeaderBinding
import com.foodandservice.databinding.ProductDietaryRestrictionItemBinding
import com.foodandservice.domain.model.restaurant_details.RestaurantProductDietaryRestrictionItem

class RestaurantProductDietaryRestrictionAdapter :
    ListAdapter<RestaurantProductDietaryRestrictionItem, RecyclerView.ViewHolder>(
        DietaryRestrictionsDiffCallback()
    ) {

    companion object {
        val REFERENCE_ICON = hashMapOf(
            "3d3cb1c3-1056-4967-8fc1-31fc9b9fcf6d" to R.drawable.celery,
            "f13d0c6e-27f1-4a25-84cb-2d08918e9a22" to R.drawable.celiac,
            "5d5e3b5e-ef5c-4e31-a71d-94a2f2a0df22" to R.drawable.cereal,
            "be290d47-ee8d-4bcf-bb4d-24f4d4dc4e28" to R.drawable.crustacean,
            "58d8c3d3-f9f3-4d16-9360-cb27b1e46377" to R.drawable.diabetics,
            "a0d087d7-8e9b-4ba6-bf35-1d63a8e439a3" to R.drawable.egg,
            "ef5f206d-7be8-4fdd-83a2-44f594a7a8a9" to R.drawable.fish,
            "2c00f117-b14d-413f-94b1-9b7840c981d7" to R.drawable.gluten,
            "e8a912a3-7c3b-4f10-a7d8-9bfc2a75fc26" to R.drawable.lactose,
            "5c5aa5b5-5d3a-4a8b-981e-7b22c35a28d7" to R.drawable.lupins,
            "f03fa11a-09a4-4d4f-a231-49a2a8570e7a" to R.drawable.milk,
            "a85b61a8-df38-403d-82da-a594a07ba046" to R.drawable.mollusks,
            "c694a0cb-3640-4df9-9a28-9f6155d1ec3f" to R.drawable.mustard,
            "07740662-c632-4589-9242-07bc7d88baf1" to R.drawable.nuts,
            "d8b4bfea-3f71-4d2c-9553-3c43738308d9" to R.drawable.pasteurized,
            "daa4d4de-4ba4-4f49-bb11-0d19fa628b96" to R.drawable.peanut,
            "8f4e2276-7851-4f48-aab2-9f0c6d2b6a07" to R.drawable.sesame,
            "bb8daa85-46d3-44eb-84b5-c8e5f2d28e52" to R.drawable.soy,
            "e247680c-8eb5-4aaf-a5d5-281b5c9b5df4" to R.drawable.sulphites,
            "aab84552-f947-4d3a-b3e9-87d9f9f0edc7" to R.drawable.vegan,
            "47a78a2f-9ca4-4c8d-a473-4f6596a501db" to R.drawable.vegetarian
        )

        const val VIEW_TYPE_HEADER = 0
        const val VIEW_TYPE_ITEM = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_HEADER) {
            val binding = ProductDietaryRestrictionHeaderBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            HeaderViewHolder(binding)
        } else {
            val binding = ProductDietaryRestrictionItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            ItemViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = getItem(position)) {
            is RestaurantProductDietaryRestrictionItem.Header -> (holder as HeaderViewHolder).bind(
                item
            )

            is RestaurantProductDietaryRestrictionItem.Item -> (holder as ItemViewHolder).bind(item)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is RestaurantProductDietaryRestrictionItem.Header -> VIEW_TYPE_HEADER
            is RestaurantProductDietaryRestrictionItem.Item -> VIEW_TYPE_ITEM
        }
    }

    class HeaderViewHolder(private val binding: ProductDietaryRestrictionHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(header: RestaurantProductDietaryRestrictionItem.Header) {
            when (header.title) {
                "Allergens" -> {
                    binding.tvDietaryRestrictionCategory.text =
                        binding.root.context.getString(R.string.product_dietary_restriction_category_allergens)
                }

                "Intolerances" -> {
                    binding.tvDietaryRestrictionCategory.text =
                        binding.root.context.getString(R.string.product_dietary_restriction_category_intolerances)
                }

                "Others" -> {
                    binding.tvDietaryRestrictionCategory.text =
                        binding.root.context.getString(R.string.product_dietary_restriction_category_others)
                }
            }
        }
    }

    class ItemViewHolder(private val binding: ProductDietaryRestrictionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RestaurantProductDietaryRestrictionItem.Item) {
            val icon = REFERENCE_ICON[item.dietarytRestriction.id]

            icon?.let { iconFound ->
                binding.apply {
                    ivDietaryRestriction.setImageDrawable(
                        ResourcesCompat.getDrawable(
                            binding.root.resources, iconFound, null
                        )
                    )

                    tvDietaryRestrictionName.text = item.dietarytRestriction.name
                }
            }
        }
    }
}

class DietaryRestrictionsDiffCallback :
    DiffUtil.ItemCallback<RestaurantProductDietaryRestrictionItem>() {
    override fun areItemsTheSame(
        oldItem: RestaurantProductDietaryRestrictionItem,
        newItem: RestaurantProductDietaryRestrictionItem
    ): Boolean {
        return if (oldItem is RestaurantProductDietaryRestrictionItem.Header && newItem is RestaurantProductDietaryRestrictionItem.Header) {
            oldItem.title == newItem.title
        } else if (oldItem is RestaurantProductDietaryRestrictionItem.Item && newItem is RestaurantProductDietaryRestrictionItem.Item) {
            oldItem.dietarytRestriction == newItem.dietarytRestriction
        } else {
            false
        }
    }

    override fun areContentsTheSame(
        oldItem: RestaurantProductDietaryRestrictionItem,
        newItem: RestaurantProductDietaryRestrictionItem
    ): Boolean {
        return oldItem == newItem
    }
}