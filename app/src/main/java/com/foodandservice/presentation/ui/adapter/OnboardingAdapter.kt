package com.foodandservice.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.foodandservice.databinding.FragmentOnboardingItemBinding
import com.foodandservice.domain.model.OnboardingItem

class OnboardingAdapter(private var list: List<OnboardingItem>) :
    RecyclerView.Adapter<OnboardingAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class ViewHolder private constructor(private val binding: FragmentOnboardingItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: OnboardingItem) {
            binding.tvOnboardingTitle.text = item.title
            binding.tvOnboardingDescription.text = item.description
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FragmentOnboardingItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}