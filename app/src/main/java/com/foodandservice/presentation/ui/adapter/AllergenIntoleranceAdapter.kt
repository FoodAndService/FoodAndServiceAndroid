package com.foodandservice.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.foodandservice.R
import com.foodandservice.databinding.ItemAllergenIntoleranceBinding
import com.foodandservice.domain.model.AllergenIntolerance

class AllergenIntoleranceAdapter :
    ListAdapter<AllergenIntolerance, AllergenIntoleranceAdapter.ViewHolder>(
        AllergenIntoleranceDiffCallback()
    ) {

    companion object {
        val DRAWABLE_REF = hashMapOf(
            "celery" to R.drawable.celery,
            "celiac" to R.drawable.celiac,
            "crustacean" to R.drawable.crustacean,
            "diabetics" to R.drawable.diabetics,
            "egg" to R.drawable.egg,
            "fish" to R.drawable.fish,
            "gluten" to R.drawable.gluten,
            "lactose" to R.drawable.lactose,
            "lupins" to R.drawable.lupins,
            "milk" to R.drawable.milk,
            "mollusks" to R.drawable.mollusks,
            "mustard" to R.drawable.mustard,
            "nuts" to R.drawable.nuts,
            "pasteurized" to R.drawable.pasteurized,
            "peanut" to R.drawable.peanut,
            "sesame" to R.drawable.sesame,
            "soy" to R.drawable.soy,
            "sulphites" to R.drawable.sulphites,
            "vegan" to R.drawable.vegan,
            "vegetarian" to R.drawable.vegetarian,
        )

        val STRING_REF = hashMapOf(
            "celery" to R.string.food_allergy_intolerance_celery,
            "celiac" to R.string.food_allergy_intolerance_celiac,
            "crustacean" to R.string.food_allergy_intolerance_crustacean,
            "diabetics" to R.string.food_allergy_intolerance_diabetics,
            "egg" to R.string.food_allergy_intolerance_egg,
            "fish" to R.string.food_allergy_intolerance_fish,
            "gluten" to R.string.food_allergy_intolerance_gluten,
            "lactose" to R.string.food_allergy_intolerance_lactose,
            "lupins" to R.string.food_allergy_intolerance_lupins,
            "milk" to R.string.food_allergy_intolerance_milk,
            "mollusks" to R.string.food_allergy_intolerance_mollusks,
            "mustard" to R.string.food_allergy_intolerance_mustard,
            "nuts" to R.string.food_allergy_intolerance_nuts,
            "pasteurized" to R.string.food_allergy_intolerance_pasteurized,
            "peanut" to R.string.food_allergy_intolerance_peanut,
            "sesame" to R.string.food_allergy_intolerance_sesame,
            "soy" to R.string.food_allergy_intolerance_soy,
            "sulphites" to R.string.food_allergy_intolerance_sulphites,
            "vegan" to R.string.food_allergy_intolerance_vegan,
            "vegetarian" to R.string.food_allergy_intolerance_vegetarian,
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

        fun bind(item: AllergenIntolerance) {
            val icon = DRAWABLE_REF[item.name.lowercase()]
            val text = STRING_REF[item.name.lowercase()]

            icon?.let { iconFound ->
                binding.ivAllergenIntolerance.setImageDrawable(
                    ResourcesCompat.getDrawable(
                        binding.root.resources, iconFound, null
                    )
                )
                text?.let { textFound ->
                    binding.tvAllergenIntolerance.text = binding.root.resources.getString(textFound)
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

class AllergenIntoleranceDiffCallback : DiffUtil.ItemCallback<AllergenIntolerance>() {
    override fun areItemsTheSame(
        oldItem: AllergenIntolerance, newItem: AllergenIntolerance
    ): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(
        oldItem: AllergenIntolerance, newItem: AllergenIntolerance
    ): Boolean {
        return oldItem == newItem
    }
}