package com.foodandservice.presentation.ui.restaurant_reviews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.foodandservice.databinding.FragmentRestaurantReviewsBinding
import com.foodandservice.presentation.ui.adapter.RestaurantReviewAdapter
import com.foodandservice.util.RecyclerViewItemDecoration
import com.foodandservice.util.extensions.CoreExtensions.navigate
import com.foodandservice.util.extensions.CoreExtensions.navigateBack
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get

class RestaurantReviewsFragment : Fragment() {
    private lateinit var binding: FragmentRestaurantReviewsBinding
    private lateinit var restaurantReviewAdapter: RestaurantReviewAdapter
    private val viewModel: RestaurantReviewsViewModel = get()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentRestaurantReviewsBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapters()

        viewModel.getRestaurantReviews(restaurantId = "1")

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.restaurantReviewsState.collect { state ->
                    when (state) {
                        is RestaurantReviewsState.Success -> {
                            restaurantReviewAdapter.submitList(state.restaurantReviews)
                        }
                        is RestaurantReviewsState.Loading -> {
                            setLoadingState()
                        }
                        is RestaurantReviewsState.Error -> {

                        }
                        is RestaurantReviewsState.Idle -> {
                            setIdleState()
                        }
                    }
                }
            }
        }

        binding.apply {
            rvRestaurantReview.addItemDecoration(RecyclerViewItemDecoration(topMargin = 32))

            btnBack.setOnClickListener {
                navigateBack()
            }

            btnAddReview.setOnClickListener {
                navigate(RestaurantReviewsFragmentDirections.actionRestaurantReviewsFragmentToReviewCreateFragment())
            }
        }
    }

    private fun setIdleState() {

    }

    private fun setLoadingState() {

    }

    private fun setAdapters() {
        restaurantReviewAdapter = RestaurantReviewAdapter().also { adapter ->
            binding.rvRestaurantReview.adapter = adapter
        }
    }
}