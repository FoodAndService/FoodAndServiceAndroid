package com.foodandservice.presentation.ui.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.foodandservice.R
import com.foodandservice.databinding.FragmentFavouritesBinding
import com.foodandservice.domain.model.FavouriteRestaurant
import com.foodandservice.presentation.ui.adapter.FavouriteAdapter

class FavouritesFragment : Fragment(), FavouriteAdapter.FavouriteRestaurantClickListener {
    private lateinit var binding: FragmentFavouritesBinding
    private lateinit var favouritesAdapter: FavouriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_favourites,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()

        val favouriteRestaurants = listOf(
            FavouriteRestaurant(
                "Rosario's Burger"
            ),
            FavouriteRestaurant(
                "Domino's Pizza"
            ),
            FavouriteRestaurant(
                "Five Guys"
            ),
            FavouriteRestaurant(
                "Foster Hollywood"
            ),
            FavouriteRestaurant(
                "La Calle Burger"
            )
        )

        favouritesAdapter.submitList(favouriteRestaurants)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
//            viewModel.favouritesState.collect { state ->
//                when (state) {
//                    is FavouritesState.Success -> {
//                        favouritesAdapter.submitList(state.favourites)
//                    }
//                    is FavouritesState.Loading -> {
//                        TODO("Show loading")
//                    }
//                    is FavouritesState.Error -> {
//                        TODO("Error")
//                    }
//                    is FavouritesState.Empty -> {}
//                }
//            }
        }
    }

    private fun setAdapter() {
        favouritesAdapter = FavouriteAdapter(this)
        binding.rvFavourites.adapter = favouritesAdapter
    }

    override fun onClick(item: FavouriteRestaurant) {

    }
}