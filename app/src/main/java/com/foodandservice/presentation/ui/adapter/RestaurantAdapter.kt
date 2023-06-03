package com.foodandservice.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.foodandservice.R
import com.foodandservice.databinding.ItemRestaurantBinding
import com.foodandservice.domain.model.location.Coordinate
import com.foodandservice.domain.model.restaurant.Restaurant
import com.foodandservice.util.LocationUtils

class RestaurantAdapter constructor(private val listener: RestaurantClickListener) :
    PagingDataAdapter<Restaurant, RestaurantAdapter.ViewHolder>(RestaurantDiffCallBack()) {

    interface RestaurantClickListener {
        fun onClick(restaurant: Restaurant)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it, listener) }
    }

    class ViewHolder private constructor(private val binding: ItemRestaurantBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Restaurant, restaurantClickListener: RestaurantClickListener) {
            binding.apply {
                tvRestaurantName.text = item.name
                tvCategoryName.text = item.categoryName
                cvOpeningStatus.visibility =
                    if (item.isClosed() || item.isOnVacation()) View.VISIBLE else View.GONE
                tvOpeningStatus.text =
                    if (item.isClosed() || item.isOnVacation()) binding.root.resources.getString(R.string.restaurant_opening_status_closed)
                    else binding.root.resources.getString(R.string.restaurant_opening_status_closed)

                if (!LocationUtils.defaultCoordinates) {
                    val distance = LocationUtils.getDistanceInKmBetweenTwoCoordinates(
                        firstCoordinate = Coordinate(
                            latitude = item.address.latitude, longitude = item.address.longitude
                        ), secondCoordinate = Coordinate(
                            latitude = LocationUtils.getUserCoordinates().latitude,
                            longitude = LocationUtils.getUserCoordinates().longitude
                        )
                    )
                    tvDistance.apply {
                        text = binding.root.resources.getString(
                            R.string.restaurant_details_distance, distance
                        )
                        setTextColor(
                            ContextCompat.getColor(
                                binding.root.context, R.color.text_normal
                            )
                        )
                    }
                    item.distanceInKm = distance
                } else {
                    tvDistance.apply {
                        text = binding.root.resources.getString(R.string.activate_location)
                        setTextColor(ContextCompat.getColor(binding.root.context, R.color.orange))
                    }
                }

                Glide.with(itemView).load(item.logo).centerCrop().into(restLogo)
                Glide.with(itemView).load(item.banner).into(restBackground)

                root.setOnClickListener {
                    restaurantClickListener.onClick(item)
                }
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemRestaurantBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class RestaurantDiffCallBack : DiffUtil.ItemCallback<Restaurant>() {
    override fun areItemsTheSame(oldItem: Restaurant, newItem: Restaurant) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Restaurant, newItem: Restaurant) = oldItem == newItem
}