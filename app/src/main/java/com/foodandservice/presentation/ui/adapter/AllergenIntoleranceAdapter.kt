package com.foodandservice.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.foodandservice.R
import com.foodandservice.databinding.ItemAllergenIntoleranceBinding
import com.foodandservice.domain.model.restaurant_details.RestaurantProductAllergenIntolerance

class AllergenIntoleranceAdapter :
    ListAdapter<RestaurantProductAllergenIntolerance, AllergenIntoleranceAdapter.ViewHolder>(
        AllergenIntoleranceDiffCallback()
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
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder private constructor(private val binding: ItemAllergenIntoleranceBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RestaurantProductAllergenIntolerance) {
            val icon = REFERENCE_ICON[item.id]

            icon?.let { iconFound ->
                binding.apply {
                    ivAllergenIntolerance.setImageDrawable(
                        ResourcesCompat.getDrawable(
                            binding.root.resources, iconFound, null
                        )
                    )

                    tvAllergenIntolerance.text = item.name
                }
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemAllergenIntoleranceBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class AllergenIntoleranceDiffCallback :
    DiffUtil.ItemCallback<RestaurantProductAllergenIntolerance>() {
    override fun areItemsTheSame(
        oldItem: RestaurantProductAllergenIntolerance, newItem: RestaurantProductAllergenIntolerance
    ) = oldItem.name == newItem.name

    override fun areContentsTheSame(
        oldItem: RestaurantProductAllergenIntolerance, newItem: RestaurantProductAllergenIntolerance
    ) = oldItem == newItem
}