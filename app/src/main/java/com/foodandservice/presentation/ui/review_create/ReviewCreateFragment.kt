package com.foodandservice.presentation.ui.review_create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.foodandservice.databinding.FragmentReviewCreateBinding
import com.foodandservice.util.extensions.CoreExtensions.navigateBack
import org.koin.android.ext.android.get

class ReviewCreateFragment : Fragment() {
    private lateinit var binding: FragmentReviewCreateBinding
    private val viewModel: ReviewCreateViewModel = get()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentReviewCreateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.createReviewState.collect { state ->
                when (state) {
                    is ReviewCreateState.Success -> {

                    }
                    is ReviewCreateState.Error -> {

                    }
                    is ReviewCreateState.Idle -> {

                    }
                }
            }
        }

        binding.apply {
            btnSendReview.setOnClickListener {

            }

            btnBack.setOnClickListener {
                navigateBack()
            }

            ratingBarReview.onRatingBarChangeListener =
                RatingBar.OnRatingBarChangeListener { ratingBar, rating, _ ->
                    if (rating < 1.0f) ratingBar.rating = 1.0f
                }
        }
    }
}