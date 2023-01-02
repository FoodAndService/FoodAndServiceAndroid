package com.foodandservice.presentation.ui.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.foodandservice.databinding.FragmentFavouritesBinding
import com.foodandservice.domain.model.FavouriteRestaurant
import com.foodandservice.presentation.ui.adapter.FavouriteAdapter
import com.foodandservice.util.extensions.CoreExtensions.navigate

class FavouritesFragment : Fragment(), FavouriteAdapter.FavouriteRestaurantClickListener {
    private lateinit var binding: FragmentFavouritesBinding
    private lateinit var favouriteAdapter: FavouriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {

        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            navigate(FavouritesFragmentDirections.actionFavouritesFragmentToHomeFragment())
        }
    }

    private fun setAdapter() {
        val favouriteRestaurants = listOf(
            FavouriteRestaurant(
                "Rosario's Burger"
            ), FavouriteRestaurant(
                "Domino's Pizza"
            ), FavouriteRestaurant(
                "Five Guys"
            ), FavouriteRestaurant(
                "Foster Hollywood"
            ), FavouriteRestaurant(
                "La Calle Burger"
            )
        )

        favouriteAdapter = FavouriteAdapter(this).also { adapter ->
            binding.rvFavourites.adapter = adapter
            adapter.submitList(favouriteRestaurants)
        }
    }

    override fun onClick(item: FavouriteRestaurant) {

    }
}