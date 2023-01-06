package com.foodandservice.presentation.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.foodandservice.R
import com.foodandservice.databinding.ItemRestaurantReviewClientBinding
import com.foodandservice.databinding.ItemRestaurantReviewOwnerBinding
import com.foodandservice.domain.model.RestaurantReview
import java.time.ZoneId
import java.util.*
import java.util.concurrent.TimeUnit

class RestaurantReviewAdapter :
    ListAdapter<RestaurantReview, RestaurantReviewAdapter.AbstractViewHolder>(
        RestaurantReviewDiffCallback()
    ) {

    companion object {
        private const val ITEM_REVIEW_CLIENT = 1
        private const val ITEM_REVIEW_OWNER = 2
    }

    abstract class AbstractViewHolder(
        val binding: ViewDataBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        abstract fun bind(item: RestaurantReview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbstractViewHolder {
        return if (viewType == ITEM_REVIEW_CLIENT) ReviewClientViewHolder.from(parent)
        else ReviewOwnerViewHolder.from(parent)
    }

    internal class ReviewClientViewHolder private constructor(binding: ItemRestaurantReviewClientBinding) :
        AbstractViewHolder(binding) {

        @SuppressLint("SetTextI18n")
        override fun bind(item: RestaurantReview) {
            val binding = binding as ItemRestaurantReviewClientBinding

            val diffInMillis =
                Calendar.getInstance().timeInMillis - item.date.atZone(ZoneId.systemDefault())
                    .toInstant().toEpochMilli()
            val diffInDays = TimeUnit.MILLISECONDS.toDays(diffInMillis)

            binding.apply {
                tvClientName.text = item.clientName
                tvDate.text = itemView.context.getString(R.string.tv_days_ago, diffInDays)
                rating.rating = item.rating
                tvRating.text = item.rating.toString()
                tvReviewDescription.text = item.description
            }
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ReviewClientViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding =
                    ItemRestaurantReviewClientBinding.inflate(layoutInflater, parent, false)
                return ReviewClientViewHolder(binding)
            }
        }
    }

    internal class ReviewOwnerViewHolder private constructor(binding: ItemRestaurantReviewOwnerBinding) :
        AbstractViewHolder(binding) {

        @SuppressLint("SetTextI18n")
        override fun bind(item: RestaurantReview) {
            val binding = binding as ItemRestaurantReviewOwnerBinding

            val diffInMillis =
                Calendar.getInstance().timeInMillis - item.date.atZone(ZoneId.systemDefault())
                    .toInstant().toEpochMilli()
            val diffInDays = TimeUnit.MILLISECONDS.toDays(diffInMillis)

            binding.apply {
                tvClientName.text = itemView.context.getString(R.string.tv_review_owner_answer)
                tvDate.text = itemView.context.getString(R.string.tv_days_ago, diffInDays)
                tvReviewDescription.text = item.description
            }

            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ReviewOwnerViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding =
                    ItemRestaurantReviewOwnerBinding.inflate(layoutInflater, parent, false)
                return ReviewOwnerViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: AbstractViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return if (this.currentList[position].isOwnerAnswer) ITEM_REVIEW_OWNER else ITEM_REVIEW_CLIENT
    }
}

class RestaurantReviewDiffCallback : DiffUtil.ItemCallback<RestaurantReview>() {
    override fun areItemsTheSame(oldItem: RestaurantReview, newItem: RestaurantReview) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: RestaurantReview, newItem: RestaurantReview) =
        oldItem == newItem
}