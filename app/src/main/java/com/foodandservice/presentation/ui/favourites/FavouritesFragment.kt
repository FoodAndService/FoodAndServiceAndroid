package com.foodandservice.presentation.ui.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.foodandservice.databinding.FragmentFavouritesBinding
import com.foodandservice.domain.model.FavouriteRestaurant
import com.foodandservice.presentation.ui.adapter.FavouriteAdapter
import com.foodandservice.util.extensions.CoreExtensions.navigate
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get

class FavouritesFragment : Fragment(), FavouriteAdapter.FavouriteRestaurantClickListener {
    private lateinit var binding: FragmentFavouritesBinding
    private lateinit var favouriteAdapter: FavouriteAdapter
    private val viewModel: FavouritesViewModel = get()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.favouritesState.collect { state ->
                    when (state) {
                        is FavouritesState.Success -> {
                            favouriteAdapter.submitList(state.favouriteRestaurants)
                        }
                        is FavouritesState.Loading -> {
                            setLoadingState()
                        }
                        is FavouritesState.Error -> {

                        }
                        is FavouritesState.Idle -> {
                            setIdleState()
                        }
                    }
                }
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            navigate(FavouritesFragmentDirections.actionFavouritesFragmentToHomeFragment())
        }
    }

    private fun setAdapter() {
        favouriteAdapter = FavouriteAdapter(this).also { adapter ->
            binding.rvFavourites.adapter = adapter
        }
    }

    override fun onClick(item: FavouriteRestaurant) {

    }

    private fun setLoadingState() {

    }

    private fun setIdleState() {

    }
}